package cn.edu.bdu.carmanage.controller.pay;

import cn.edu.bdu.carmanage.config.AlipayConfig;
import cn.edu.bdu.carmanage.entity.pay.UserOrderAlipay;
import cn.edu.bdu.carmanage.entity.user.User;
import cn.edu.bdu.carmanage.mapper.UserOrderAlipayMapper;
import cn.edu.bdu.carmanage.mapper.UserPayMapper;
import cn.edu.bdu.carmanage.service.cms.user.UserService;
import cn.edu.bdu.carmanage.utils.CardNumberUtils;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static cn.edu.bdu.carmanage.config.AlipayConfig.*;

/**
 * @Author WU
 * @Date 2020/2/24 22:21
 * @Version 1.0
 */
@Controller
@RequestMapping("/alipay")
public class PayController {

    public static final Logger LOGGER = LoggerFactory.getLogger(PayController.class);

    @Autowired
    private UserPayMapper userPayMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserOrderAlipayMapper userOrderAlipayMapper;

    @GetMapping("/goPay")
    public String index(BigDecimal tmoney, String val, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (tmoney != null || val != null) {
            session.setAttribute("tmoney", tmoney);
            session.setAttribute("val", val);
        }
        return "alipay/index.html";
    }


    @GetMapping(value = "/go/applytest", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String applytest(HttpServletRequest request, HttpServletResponse response, @RequestParam("money") String money) throws IOException, AlipayApiException {
        //获得初始化的AlipayClient
//        PrintWriter out = response.getWriter();
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrL, app_id, merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String tmoney = money;
        String val = "";
        String ddh = "JFDD" + CardNumberUtils.getCardNumber();
        System.out.println(tmoney + "这是跳转的" + val + "ddh" + ddh);
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = ddh;
        //付款金额，必填
        String total_amount = tmoney;
        //订单名称，必填
        String subject = "个人充值";
        //商品描述，可空
        String body = "余额充值";
        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "5m";
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"timeout_express\":\"" + timeout_express + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
        String url = alipayClient.pageExecute(alipayRequest).getBody();
        System.out.println(url);
        UserOrderAlipay userOrderAlipay = new UserOrderAlipay();
        userOrderAlipay.setUserId(user.getId());
        userOrderAlipay.setOuTradeNo(out_trade_no);
        userOrderAlipay.setSubject(subject);
        userOrderAlipay.setTotalAmount(total_amount);
        userOrderAlipay.setBody(body);
        userOrderAlipay.setDate(new Date());
        userOrderAlipayMapper.insert(userOrderAlipay);

        //输出

//        request.setAttribute("user",user);
//        response.setContentType("text/html;charset=utf-8");
//        out.print(doc.outerHtml());
//      return result;
//        out.print(url);
        return url;

    }


    @GetMapping("/returnUrl")
    public String returnUrl(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr) throws UnsupportedEncodingException, AlipayApiException {
        response.setContentType("text/html;charset=utf-8");
        boolean verifyResult = rsaCheckV1(request);
        if (verifyResult) {
            //验证成功
            //请在这里加上商户的业务逻辑程序代码，如保存支付宝交易号
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
           /* String ids = (String) request.getSession().getAttribute("ids");
            if (!StringUtils.isEmpty(ids)) {
                String[] split = ids.split(",");
                for (String s : split) {

                }
            }*/
            UserOrderAlipay userOrderAlipay = this.userOrderAlipayMapper.selectOne(new QueryWrapper<UserOrderAlipay>().eq("out_trade_no", out_trade_no));
            String total_amount = userOrderAlipay.getTotalAmount();
            String userId = userOrderAlipay.getUserId();

            UserOrderAlipay userOrder = new UserOrderAlipay();
            BeanUtils.copyProperties(userOrderAlipay, userOrder);
            userOrder.setStatus(1);
            this.userOrderAlipayMapper.updateById(userOrder);

            Double money = Double.parseDouble(total_amount);
            this.userService.addUserMoney(userId, money);
            this.userService.addPayOrder(userId, userOrderAlipay.getBody(), money);

            attr.addAttribute("userId", userId);//跳转地址带上test参数
            return "redirect:/user/user/getUser";

        } else {
            return "redirect:error";

        }

    }


    public boolean rsaCheckV1(HttpServletRequest request) {
        // https://docs.open.alipay.com/54/106370
        // 获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        try {
            boolean verifyResult = AlipaySignature.rsaCheckV1(params,
                    AlipayConfig.merchant_private_key,
                    AlipayConfig.charset,
                    AlipayConfig.sign_type);

            return verifyResult;
        } catch (AlipayApiException e) {

            return true;
        }
    }


    @RequestMapping("notify")
    public String notify(HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException {
        // 一定要验签，防止黑客篡改参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder notifyBuild = new StringBuilder("/****************************** alipay notify ******************************/\n");
        parameterMap.forEach((key, value) -> notifyBuild.append(key + "=" + value[0] + "\n"));
//        log.info(notifyBuild.toString());


        boolean flag = this.rsaCheckV1(request);
        if (flag) {
            /**
             * TODO 需要严格按照如下描述校验通知数据的正确性
             *
             * 商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
             * 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
             * 同时需要校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
             *
             * 上述有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
             * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
             * 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
             */

            User user = (User) request.getSession().getAttribute("user");
            //交易状态
            String tradeStatus = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
            // 商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
            // TRADE_FINISHED(表示交易已经成功结束，并不能再对该交易做后续操作);
            // TRADE_SUCCESS(表示交易已经成功结束，可以对该交易做后续操作，如：分润、退款等);
            if (tradeStatus.equals("TRADE_FINISHED")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，
                // 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                //如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
            } else if (tradeStatus.equals("TRADE_SUCCESS")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，
                // 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。

            }

            return "redirect:view";
        }

        return "redirect:view";
    }


}

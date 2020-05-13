package cn.edu.bdu.carmanage.controller.verifyCode;

import cn.edu.bdu.carmanage.entity.sms.SmsModel;

import cn.edu.bdu.carmanage.service.cms.sms.SendSmsService;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author: Winston
 * @createTime: 2020/5/11
 */
@Controller
@RequestMapping("user/verifyCode")
public class VerifyCodeController {

    public static final int LENGTH = 6; // 验证码长度
    @Autowired
    private SendSmsService sendSmsService;
    @GetMapping("/getVerifyCode")
    public void getVerifyCode(@RequestParam("useType") Integer useType) throws ClientException {

        SmsModel smsModel = new SmsModel();
        smsModel.setMobile("17749707027");
        smsModel.setTemplateCode("您的验证码为：${code}，该验证码5分钟内有效，请勿泄漏于他人！");
        smsModel.setTemplateParam("135566");
        SendSmsResponse send = sendSmsService.send(smsModel);
      /*  sendSmsService.send(smsModel);
        String verifyKey = RandomUtil.simpleUUID();
        String key = NotifyRedisKeys.verifyCodeKey(useType, verifyKey);
        //生成验证码
        String code = RandomUtil.randomNumbers(LENGTH);*/


    }

}

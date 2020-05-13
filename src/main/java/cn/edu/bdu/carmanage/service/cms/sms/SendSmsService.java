package cn.edu.bdu.carmanage.service.cms.sms;

import cn.edu.bdu.carmanage.entity.sms.SmsModel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author: Winston
 * @createTime: 2020/5/11
 */
@Service
public class SendSmsService {
    private static final Logger log = LoggerFactory.getLogger(SendSmsService.class);
    @Value("Dysmsapi}")
    private String product;

    @Value("dysmsapi.aliyuncs.com")
    private String domain;

    @Value("LTAI4GGsVoAhyGmfexGxDkQz")
    private String accessKeyId;

    @Value("8iZX97IfC83TNEpGktMsYIOTMIht0A")
    private String accessKeySecret;

    @Value("cn-hangzhou")
    private String endpointName;

    @Value("cn-hangzhou")
    private String regionId;

    @Value("10000")
    private String defaultConnectTimeout;

    @Value("10000")
    private String defaultReadTimeout;

    @Value("紫竹小区")
    private String signName;
    @Autowired
    private IAcsClient iAcsClient;

    public SendSmsResponse send(SmsModel smsModel) throws ClientException {
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(smsModel.getMobile());
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(smsModel.getTemplateCode());
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam(JSONObject.toJSONString(smsModel.getTemplateParam()));
        request.setOutId(UUID.randomUUID().toString());
        //请求失败这里会抛ClientException异常
        return acsClient.getAcsResponse(request);

    }

}

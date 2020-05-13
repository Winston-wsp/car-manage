package cn.edu.bdu.carmanage.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class SmsConfiguration {

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

    @Bean
    public IAcsClient iAcsClient() throws ClientException {

        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", defaultConnectTimeout);
        System.setProperty("sun.net.client.defaultReadTimeout",defaultReadTimeout);

        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        try {
            DefaultProfile.addEndpoint(endpointName, regionId, product,domain);
        } catch (ClientException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
        return new DefaultAcsClient(profile);
    }
}

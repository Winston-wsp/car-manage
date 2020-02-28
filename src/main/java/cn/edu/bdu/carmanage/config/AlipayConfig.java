package cn.edu.bdu.carmanage.config;

import org.springframework.context.annotation.Configuration;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

@Configuration
public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101900723244";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCSwLUjKNoj34OeXaNeahwX2n0Bw5IZ+zSzbKOcBchzU7T7M9N8AE+DcUtUZIshuvK+CVorIj6k+Lgl2ftmkCeLqVhkXx3plSlTwwJ4JUx3PSYU/SFAOCauDRSVlbNKRjmd25hULJtGevnZ1EtT8HRDKbjfUxRZwSCQ1yGuQChOlgIBC+EgWTNfMhKtYs/eHTg/OeWd+vY240/Lgqyp9YXoE4dicoJiVid94rGQ8ei5aAJ7gWVuQeCN8QxfYNvMjbZiEtBVEDLN3K4djRG/RyBDx/W35/zYLhwuF2Y3/mhPDDRP05huvZ4SZ135puTGZWE/MEeDBJqNkQWE+83y4299AgMBAAECggEAQEHRDowcuAhugTFPD1A27NrmUZdoqaes88NmADMoChdk73lJ6MnaBxvceB9uLF7HWx6NNMXR2Dpvp2NDCS2loymLYqejWVvOtLZ2plTunmdP/XC41n2/sgZYNQdPcf49zDkTAghmFP5T2GV0dlSbE615HZArIQHHWKRaQwoknAO3eZGc4j1w0aSeyTOFBPcIFCXqapKMV6nYz5OIdeKhsGODypvydpnuChGtDxIcCUiiWWTep7WaO1d+e3vNf3UBA1jap/V1oP6RmcEqGxJcmDN8JEze6tCDPK1mAyQtFED3NA2d7g235uhVlAmsHPTU40jIDhnvdCDmiyOVq/Y9oQKBgQDfXZahg4J25h/aprFWoGKZymviOOu9OI+/fC+1eXuhu6gEW8zsM9Dw9xFoWoAU+bgoPWezqdemYby6P8wRhDnCKrz8ueTKtZ7IULhZ4GactsgZpLm4de9bb79Z5VsG+UYqM/ayQIYP/q4xrUuiKlzpnOzFghtci8PW4D3c/FttrwKBgQCoMZ1xZMslrcOjUBx4qnV9K4bfEEzlHYffcfh4Nih2mG0LbbSr7S2+qaXmydQlSwdRe1d/1u7QLnkIX2+ZJtVpr6V99M7KmPQmh11Io+Lxgd1+FYbg5f9AhNCjMRNI2gmYTVmQE7TlmvcUx2ctbNmPwWa79wtg2jBKJ+YdW8TMkwKBgBUqRxWy4m/lymrrlK5CsFUmSya6ggwCCgz49YRaqI5gu8D9+eL/3fKXVrzupmhWaU7W0rbBhz+qzzlOxkV6pAZ0CkhgTP+wSgVtMKrDirWa1Ecpxrdl5XJiml0oUIHOqQlityOY3ZsyZMssQphxLzCrv/vPIkyYqcv4n6QK/ZbdAoGAKNTSqgKUJrOGeREruEd5ekek2GY2pLCEmcQrU9OiAxiB8HJTmCFeJ5k7w0lgDSWnwyo0Vb6x/ywpCdVRXFbHHH8dp/RUd26mZuwZw1a183dZdBB5zANJ8jnMBLIXQmpcRfMROR7vnYX/UbYO4EI6luGWtS8GPh759ia1gI8Uua0CgYEAxDYb2/Vt/eNwyVxvx9ftTGw+TM7kCaBWyPYfTh9oIXjjvNJL1qll6lUt1ya+nL83UtZtaTF8Ys8ewIXuFapjxl4z0oH2+4M+R3N9wLG+XZFFpAuYoXz4Ja5hiHL5t3E4EPWulmBWIoRl1PipNF/PcKrceAN86XA74xT+MugSCy4=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkJSiFx87GVQSblYEPnSvYXIeOPdvd/ipM0vBRuMGgRVjYaC+xudk9QB/IZ9Tsxz1+bG6DNw7eWq7rXZ9vdJKwB1xEs1jyNhzspE6l8O0FYz8ZwWFcujeHTD96eV16HZUUHljw73q27tyG88xQ5dkLDzDFZfgwnsjFrd550IJLevhXGeZOJ9dvgl8jY3vYaXMVwqIbR2yI15tjq2fIwSdvUBkFg9+V/9+VCD6FHnXSO/MEgOeu7u67ImPrOkaCkdB2wJjR5ZDcKctVM70G/CAyP8NOn+YNeeHgcebFU/s0dDDqQUvjUFbGs3nYx5/55l3fk2vS7/RdCxd4dqQWo9+qwIDAQAB";
	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8089/alipay/notifyUrl";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8089/alipay/returnUrl";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrL = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "D:/logs/";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


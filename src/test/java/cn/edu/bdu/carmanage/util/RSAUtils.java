package cn.edu.bdu.carmanage.util;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtils {

    private static final String KEY_ALGORITHM = "RSA";

    /**
     * 签名算法
     */
    private static final String SIGNATURE_ALGORITHM = "SHA1withRSA";

    /**
     * 加密模式
     */
    private static final String TRANSFORMATION_RSA_ECB_PKCS1 = "RSA/ECB/PKCS1Padding";

    /**
     * 秘钥长度
     */
    private static final int KEY_SIZE = 2048;

    /**
     * 用私钥对信息生成数字签名
     *
     * @param sign       已加密数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String sign(String sign, String privateKey) throws Exception {
        byte[] keyBytes = DatatypeConverter.parseBase64Binary(privateKey);
        byte[] data = DatatypeConverter.parseBase64Binary(sign);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(data);
        return DatatypeConverter.printBase64Binary(signature.sign());
    }

    /**
     * 获取公钥
     *
     * @param publicKey 公钥字符串
     * @return
     */
    public static PublicKey getPublicKey(String publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decodeBase64(publicKey.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        return keyFactory.generatePublic(keySpec);
    }


    /**
     * 获取私钥
     *
     * @param privateKey 私钥字符串
     * @return
     */
    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decodeBase64(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 校验数字签名
     *
     * @param encodedData 已加密数据
     * @param publicKey   公钥(BASE64编码)
     * @param sign        数字签名
     * @return
     * @throws Exception
     */
    public static boolean verify(String encodedData, String publicKey, String sign)
            throws Exception {
        byte[] keyBytes = DatatypeConverter.parseBase64Binary(publicKey);
        byte[] data = DatatypeConverter.parseBase64Binary(encodedData);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicK);
        signature.update(data);
        return signature.verify(DatatypeConverter.parseBase64Binary(sign));
    }

    /**
     * RSA公钥加密
     *
     * @param plainBytes 明文
     * @param keyStr     Base64处理过的KeyStr
     * @return
     * @throws Exception
     */
    public static String encryptRSA(byte[] plainBytes, String keyStr) throws Exception {
        int keyByteSize = KEY_SIZE / 8; // 密钥字节数
        int encryptBlockSize = keyByteSize - 11; // 加密块大小=密钥字节数-padding填充字节数
        int nBlock = plainBytes.length / encryptBlockSize;// 计算分段加密的block数，向上取整
        if ((plainBytes.length % encryptBlockSize) != 0) { // 余数非0，block数再加1
            nBlock += 1;
        }
        Cipher cipher = Cipher.getInstance(TRANSFORMATION_RSA_ECB_PKCS1);
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(keyStr));
        // 输出buffer，大小为nBlock个keyByteSize
        ByteArrayOutputStream outbuf = new ByteArrayOutputStream(nBlock * keyByteSize);
        // 分段加密
        for (int offset = 0; offset < plainBytes.length; offset += encryptBlockSize) {
            int inputLen = plainBytes.length - offset;
            if (inputLen > encryptBlockSize) {
                inputLen = encryptBlockSize;
            }
            // 得到分段加密结果
            byte[] encryptedBlock = cipher.doFinal(plainBytes, offset, inputLen);
            // 追加结果到输出buffer中
            outbuf.write(encryptedBlock);
        }
        outbuf.flush();
        outbuf.close();
        return Base64Utils.encode(outbuf.toByteArray());
    }

    /**
     * RSA私钥解密
     *
     * @param cryptedString 密文
     * @param priKeyStr     私钥
     * @return
     * @throws Exception
     */
    public static String decryptRSA(String cryptedString, String priKeyStr) throws Exception {
        byte[] data;
        //Base64编码转码
        data = Base64.decodeBase64(cryptedString);
        int RESERVEBYTES = 11;
        Cipher cipher = Cipher.getInstance(TRANSFORMATION_RSA_ECB_PKCS1);
        int decryptBlock = KEY_SIZE / 8; // 256 bytes
        int encryptBlock = decryptBlock - RESERVEBYTES; // 245 bytes
        // 计算分段解密的block数 (理论上应该能整除)
        int nBlock = (data.length / decryptBlock);
        // 输出buffer, , 大小为nBlock个encryptBlock
        ByteArrayOutputStream outbuf = new ByteArrayOutputStream(nBlock * encryptBlock);
        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(priKeyStr));
        // 分段解密
        for (int offset = 0; offset < data.length; offset += decryptBlock) {
            // block大小: decryptBlock 或 剩余字节数
            int inputLen = (data.length - offset);
            if (inputLen > decryptBlock) {
                inputLen = decryptBlock;
            }
            // 得到分段解密结果
            byte[] decryptedBlock = cipher.doFinal(data, offset, inputLen);
            // 追加结果到输出buffer中
            outbuf.write(decryptedBlock);
        }
        outbuf.flush();
        outbuf.close();
        return new String(outbuf.toByteArray());
    }

    public static void main(String[] args) throws Exception {
//        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCAm6wxiJ8pcTArFvQ11BoV4Ro0+PfTw886Dg+yrQ+ZrQKzXL3H10rjO8s+og3URQPcrQQq7q5P/2peNhDxl+eItkwY0O3dbAzDGo19ayQeK0GvLJAJ96sM0ZQ6u+iO+m1XWuR25k0UJo7JHfyAHh6NK5H66h9qPSQhCwrMVE5SY5Q1kCBccVNm9XO56KVsJU40vdNNJo4RKOzVd7Qg+GvbyWFxoKB18aUUVAMd5ErJv7cWroDHK0A1osYel3ObESz716WK0nr/+IoNiBZRtihhJUqv3n68F4ujmgRrtb43+2s8UuMMC/g6AfGSUxGIVNY/CitRGfYOMtF7VJ125sILAgMBAAECggEAci9FHBmMDtRzCt7vZldx7ZkVlpCyIK+KbqYLY/1pi2zjp/IggazxzXzKQErLcnrwKd7ng2K1G0vP2eOw+X+AT45jU+k0N9J2JkeN9hvJTovjXq93F5JwqQnuzJMHsOJBcDy/+Z0SNGDjh0b7Z5jWUUqs6pUx3TZ5vGi8Zpw3icehxG6UDVo3Ubx6KbSYLSJ4F21rChkYDTkKoCY3KgTcktzSMp/o/9epf1+LaTV6e1y+mG6/WqbmVf1FGWHgan5sBgb/u9USshgQSatq9Vco0I0Qn7PQaaNEcc9gdCRjFoCwweGZJlFJbSXb7r3n6XC/irkt+LqnsH46KN67QvS82QKBgQDJ68XZb4O2aCVl2N6n1JYLeWNbtQT9aTMIvpv7hWxKg5j6SDKcAjd5Y9x74VOk9RZUF1sNohCuTL04N5wBUueqG0KmMNM8n4lkB+sQKwZX1YK+i5+q1WdkMn9ae8KBntXTiU4zzyv+Fj974k4XYrbnVxx+d6ubvok6/OmTEwvZDQKBgQCjDWCqrrmvpmCIjFI8sUFADq4CX4wTE6G35PaJDRwmGOdDfOPNC2M6f41H58I4MpRATSCMGQaXZYRv3P/mUwaHFfHcbZ7CB6NQIJoT8wjscdPPH0s/UMk7Qd7pXTpyoDsvSQe5ed6BHiT602x+HbZbA/Wk6/EduBioUhMwSicRdwKBgHDVTZP5mrJ9dBh7ncBPLSFwTbNbblRlSt1iFivxHNhuvTduzMkc+/7bbMPU4DbXtgh7nKf7njKoMzyM5qJev5Hw9ceElSXVSKMbQaGhSUQfzZXI1ApUghgdl8jqS4C/T2GL4qVU6ZJswEDr7xNmWCriJMwO91hqmqjZ13QWOpKpAoGAL/zH7nPuAlwdHRLvQR2J7qhjXh9wwkyM0+p3BG7vUecSaTJgeQBY++Z+ViNHbfK03xfT9GeJIRWnnKSWS9szmJ5EyHMavlAxKGvv7N8iSTRW+iZjpwbHssD4MgZWJVYzFP+RXm0XpziP+aIv1A46lR7dNOSb2ftMhn1hWFz6kpECgYEAt8k593FWJYewhf1LgVawL6BElxR7qVj1P1vWtjwdB205bCE8yMHieslzb7p1+YghaOHNycebTb/GneGnqHK8u0+8lh9jYRL5siNrowYsfOpbtxBy5pcxgQJB2rlQA7HOIyIoHxT0INqaev5UYBd5+pjDPgOfTA7S1QqKFE9yv0Q=";
//        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgJusMYifKXEwKxb0NdQaFeEaNPj308PPOg4Psq0Pma0Cs1y9x9dK4zvLPqIN1EUD3K0EKu6uT/9qXjYQ8ZfniLZMGNDt3WwMwxqNfWskHitBryyQCferDNGUOrvojvptV1rkduZNFCaOyR38gB4ejSuR+uofaj0kIQsKzFROUmOUNZAgXHFTZvVzueilbCVONL3TTSaOESjs1Xe0IPhr28lhcaCgdfGlFFQDHeRKyb+3Fq6AxytANaLGHpdzmxEs+9elitJ6//iKDYgWUbYoYSVKr95+vBeLo5oEa7W+N/trPFLjDAv4OgHxklMRiFTWPworURn2DjLRe1SddubCCwIDAQAB";
        String privateKey =   "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIpqLi4/NCM5A8CWZkxSYznHgqIEaNZlHCQAnOFoeZz5IVaEEWHAQSqQtqsGtD7Yd8ettqqDGbcX7uqA8DHSCwEEK2v7ybBwqljsdM76hBjufSxRrEZIMUg+4qW0phOlEWTNCZ7uD3I14qpRlTWyb6dBvNDHeVFReMJ04v0ztHx/AgMBAAECgYEAhD3J/VlZYMWaNif7eLPGJW/d+RkpaJyVdDg5qon8luLW3Se0y6W5HQfB3ki7cMe0OMxZFzPGH83374hc89xvsZBp4w6J7al8NOuL8cW3kaNx2VwUzxpqjMcNXimV+wrCYCrzFiNJr2gVFM0ezmMJmmRei17qlSu3jL6xb+hbx1ECQQDVFw5Yzly5/p/vwyAjob8Rbm9wlQ182VnWu9cD1vY+LBXODN+nuLBW2gYdrXgogSGPohxTO1aERh93ikLlyPztAkEApkmPU7m0f/ukzpq/wECxVGNLM6M/5SsK2S7OywYxcOHg6zwIije0wZHZaxw/WcTMArXPFLOESjKs+Q2JeL6dmwJASMVH7AIFXqtbGTlMx35cTuqk+hCc+48KhC+/3RrTGyZZB/YThyamafKo9gegqkx3FqMpAy+XezKrWlqIQArkfQJAKBWXXUFox2krwzn+eyOdG5CL8jiqEF4d0Vi9NKebh3CLvWjNTjQ7eGXmGTHH+6W63/RfpMfZXeF1XNvshZS9fQJBALTqFA12LfXTI9LqPupfBbJEZ4Hreblfjd31aecMEK82LOPX4BCNzCfvFcSQtg21n0wgD8mO08nAtY5jrmB/wsw=";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCKai4uPzQjOQPAlmZMUmM5x4KiBGjWZRwkAJzhaHmc+SFWhBFhwEEqkLarBrQ+2HfHrbaqgxm3F+7qgPAx0gsBBCtr+8mwcKpY7HTO+oQY7n0sUaxGSDFIPuKltKYTpRFkzQme7g9yNeKqUZU1sm+nQbzQx3lRUXjCdOL9M7R8fwIDAQAB";


        //公钥加密，私钥解密
        String encryptData = encryptRSA("我是谁呢？why？".getBytes("utf-8"), publicKey);
        System.out.println("encryptData：" + encryptData);
        String data = decryptRSA(encryptData, privateKey);
        System.out.println("data：" + data);

        //签名，验签
        String sign = sign("key=123&value=", privateKey);
        System.out.println("sign：" + sign);
        boolean verify = verify("key=123&value=", publicKey, sign);
        System.out.println("verify：" + verify);
    }
}

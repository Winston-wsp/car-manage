package cn.edu.bdu.carmanage.util;

import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {

    private static final String KEY_ALGORITHM = "AES";

    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String CIPHER_ALGORITHM_ECB = "AES/ECB/PKCS5Padding";

    private static final String CHAR_SET = "UTF-8";

    /**
     * 随机生成固定长度AES密钥
     *
     * @param length 长度
     * @return
     */
    public static String generateLenString(int length) {
        char[] cResult = new char[length];
        int[] flag = {0, 0, 0}; // A-Z, a-z, 0-9
        int i = 0;
        while (flag[0] == 0 || flag[1] == 0 || flag[2] == 0 || i < length) {
            i = i % length;
            int f = (int) (Math.random() * 3 % 3);
            if (f == 0) {
                cResult[i] = (char) ('A' + Math.random() * 26);
            } else if (f == 1) {
                cResult[i] = (char) ('a' + Math.random() * 26);
            } else {
                cResult[i] = (char) ('0' + Math.random() * 10);
            }
            flag[f] = 1;
            i++;
        }
        return new String(cResult);
    }

    /**
     * AES加密
     *
     * @param plainBytes 明文字节数组
     * @param keyBytes   密钥字节数组
     * @param IV         随机向量
     * @return 加密后字节数组，不经base64编码
     */
    public static String aesEncrypt(byte[] plainBytes, byte[] keyBytes, String IV)
            throws Exception {
        // AES密钥长度为128bit、192bit、256bit，默认为128bit
        if (keyBytes.length % 8 != 0 || keyBytes.length < 16
                || keyBytes.length > 32) {
            throw new Exception("AES密钥长度不合法");
        }
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
        SecretKey secretKey = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
        if (StringUtils.trimToNull(IV) != null) {
            IvParameterSpec ivspec = new IvParameterSpec(IV.getBytes("utf-8"));
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
        } else {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        }
        byte[] encryptedBytes = cipher.doFinal(plainBytes);
        return Base64Utils.encode(encryptedBytes);
    }

    /**
     * AES对称解密
     *
     * @param message 待解密文
     * @param key     秘钥
     * @return
     * @throws Exception
     */
    public static byte[] aesDecrypt(String message, String key) throws Exception {
        // AES密钥长度为128bit、192bit、256bit，默认为128bit
        if (key.getBytes("utf-8").length % 8 != 0 || key.getBytes("utf-8").length < 16
                || key.getBytes("utf-8").length > 32) {
            throw new Exception("AES密钥长度不合法");
        }
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
        SecretKeySpec securekey = new SecretKeySpec(key.getBytes("utf-8"), KEY_ALGORITHM);// 设置加密Key
        cipher.init(Cipher.DECRYPT_MODE, securekey);
        return cipher.doFinal(Base64Utils.decode(message));
    }

    public static void main(String[] args) throws Exception {
        String secretKey = generateLenString(16);
//        String str = "123,123456,456,456,456,456,465,45,456,487,89,7,897,897,897,89,7,897,89,789,7,89,78,4,1,2,13,21";
          String str = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgJusMYifKXEwKxb0NdQaFeEaNPj308PPOg4Psq0Pma0Cs1y9x9dK4zvLPqIN1EUD3K0EKu6uT/9qXjYQ8ZfniLZMGNDt3WwMwxqNfWskHitBryyQCferDNGUOrvojvptV1rkduZNFCaOyR38gB4ejSuR+uofaj0kIQsKzFROUmOUNZAgXHFTZvVzueilbCVONL3TTSaOESjs1Xe0IPhr28lhcaCgdfGlFFQDHeRKyb+3Fq6AxytANaLGHpdzmxEs+9elitJ6//iKDYgWUbYoYSVKr95+vBeLo5oEa7W+N/trPFLjDAv4OgHxklMRiFTWPworURn2DjLRe1SddubCCwIDAQAB";

        String encryptStr = aesEncrypt(str.getBytes(CHAR_SET), secretKey.getBytes(CHAR_SET), null);
        System.out.println("密文：" + encryptStr);
        byte[] decryptStr = aesDecrypt(encryptStr, secretKey);
        System.out.println("明文：" + new String(decryptStr, CHAR_SET));
    }
}

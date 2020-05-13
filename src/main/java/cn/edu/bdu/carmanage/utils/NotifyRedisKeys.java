package cn.edu.bdu.carmanage.utils;

/**
 * @author: Winston
 * @createTime: 2020/5/11
 */
public class NotifyRedisKeys {

    public static final String REDIS_VERIFY_KEY_KEY = "b9b-verify-key-";

    /**
     * 构建保存至缓存 key-value 中的 key值
     *
     * @param useCodeType 验证码类型
     * @param verifyKey   验证码key
     * @return key值
     */
    public static String verifyCodeKey(Integer useCodeType, String verifyKey) {
        return REDIS_VERIFY_KEY_KEY + useCodeType + verifyKey;
    }
}

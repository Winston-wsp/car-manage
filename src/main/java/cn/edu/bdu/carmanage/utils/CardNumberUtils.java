package cn.edu.bdu.carmanage.utils;

/**
 * @Author WU
 * @Date 2020/2/19 13:32
 * @Version 1.0
 */
public class CardNumberUtils {
    /**
     * 以毫微秒做基础计数, 返回唯一有序增长ID
     * <pre>System.nanoTime()</pre>
     * <pre>
     *  线程数量:   100
     *  执行次数:   1000
     *  平均耗时:   222 ms
     *  数组长度:   100000
     *  Map Size:   100000
     * </pre>
     * @return  ID长度32位
     */
    public static String getCardNumber(){
        return MathUtils.makeUpNewData(Thread.currentThread().hashCode()+"", 3)+   MathUtils.randomDigitNumber(7);                                          //随机7位数
    }

}

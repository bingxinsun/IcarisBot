package xyz.satdg.sao.icaris.function;

/**
 * 一些基本功能，如取随机数，转换hash值，转换base64等
 * @author GongSunink
 */
public class BasicFunction {
    /**
     * System.currentTimeMillis() 返回从1970年1月1日开始到现在的毫秒数，为Long类型
     * 对返回的值强值被限制在[min,max]区间中
     * @param max 转int类型，然后取区间最大最小值的差值，再加上最小值，取得伪随机数
     *      * %(max-min)让区间上界
     * @param min 区间下界
     * @return 返回随机数
     */
    public static int getRand(int max,int min){
        return (int)System.currentTimeMillis()%(max-min)+min;
    }


}

package xyz.satdg.sao.icaris.api;

/**
 * 监听器种类
 * @author GongSunink
 */
public enum EventListenerType {
    /**
     * STANDARD是标准监听器，可以进行disable、enable操作
     * TEMP是临时监听器，使用完毕丢弃，之后再重新注册
     * CONSTANT是常量监听器，生命周期为bot的生命周期
     */
    STANDARD,
    TEMP,
    CONSTANT
}

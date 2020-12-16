package xyz.satdg.sao.icaris.api;

import net.mamoe.mirai.Bot;
import xyz.satdg.sao.icaris.base.EventListenerGroupStd;

/**
 * 事件接口
 * @author GongSunink
 */
public interface Observer {

     enum ObserverType {
        /**
         * STANDARD是标准监听器，可以进行disable、enable操作
         * TEMP是临时监听器，使用完毕丢弃，之后再重新注册
         * CONSTANT是常量监听器，生命周期为bot的生命周期
         */
        STANDARD,
        TEMP,
        CONSTANT
    }

    /**
     * 事件实例，每个事件都需要有此对象
     * @return 事件实例
     */
    EventListenerGroupStd listenerStd();
}

package xyz.satdg.sao.icaris.api;

import net.mamoe.mirai.Bot;
import xyz.satdg.sao.icaris.base.EventListenerGroupStd;

/**
 * 事件接口
 * @author GongSunink
 */
public interface Observer {

    /**
     * 事件实例，每个事件都需要有此对象
     * @return 事件实例
     */
    EventListenerGroupStd listenerStd();
}

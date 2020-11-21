package xyz.satdg.sao.icaris.api;

import net.mamoe.mirai.Bot;
import xyz.satdg.sao.icaris.base.EventListenerGroupStd;

/**
 * 事件接口
 * @author GongSunink
 */
public interface EventListener {
    /**
     * 日志记录
     * @param bot 机器人实例
     */
    void log(Bot bot);
    /**
     * 事件实例，每个事件都需要有此对象
     * @return 事件实例
     */
    EventListenerGroupStd listenerStd();
}

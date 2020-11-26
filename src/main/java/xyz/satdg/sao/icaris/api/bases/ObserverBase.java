package xyz.satdg.sao.icaris.api.bases;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.SimpleListenerHost;
import xyz.satdg.sao.icaris.api.Observer;

/**
 * 事件基础类
 * @author GongSunink
 */
public abstract class ObserverBase extends SimpleListenerHost implements Observer {

    @Override
    public void log(Bot bot){
        bot.getLogger().info("监听器触发<"+this.listenerStd().getListenerGroupName()+">");
    }
    @Override
    public String toString() {
        return this.listenerStd().getListenerGroupName();
    }
    @Override
    public int hashCode() {
        return this.listenerStd().getListenerGroupName().hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ObserverBase){
            return this.listenerStd().getListenerGroupName().equals(obj);
        }
        return false;
    }
}

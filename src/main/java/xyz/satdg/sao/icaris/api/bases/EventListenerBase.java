package xyz.satdg.sao.icaris.api.bases;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.SimpleListenerHost;
import xyz.satdg.sao.icaris.api.EventListener;

/**
 * �¼�������
 * @author GongSunink
 */
public abstract class EventListenerBase extends SimpleListenerHost implements EventListener {

    @Override
    public void log(Bot bot){
        bot.getLogger().info("����������<"+this.listenerStd().getListenerGroupName()+">");
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
        return this.listenerStd().getListenerGroupName().equals(obj);
    }
}

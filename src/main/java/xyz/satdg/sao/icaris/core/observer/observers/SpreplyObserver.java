package xyz.satdg.sao.icaris.core.observer.observers;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.message.MessageEvent;
import xyz.satdg.sao.icaris.api.EventListenerType;
import xyz.satdg.sao.icaris.api.bases.ObserverBase;
import xyz.satdg.sao.icaris.base.EventListenerGroupStd;
import xyz.satdg.sao.icaris.database.SPreplyTable;

/**
 * @author GongSunink
 */
public class SpreplyObserver extends ObserverBase {

    SPreplyTable sPreplyTable = new SPreplyTable();

    @Override
    public EventListenerGroupStd listenerStd() {
        return new EventListenerGroupStd("ÌØÊâ»Ø¸´¼àÌı×é", EventListenerType.STANDARD);
    }

    @EventHandler(priority = Listener.EventPriority.LOW)
    public void spreplyListener(MessageEvent event){
        if (sPreplyTable.select(event.getMessage().contentToString())!=null){
            event.getSubject().sendMessage(sPreplyTable.select(event.getMessage().contentToString()));
        }
    }
}

package xyz.satdg.sao.icaris.core.observer.observers;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.message.MessageEvent;
import xyz.satdg.sao.icaris.api.bases.ObserverBase;
import xyz.satdg.sao.icaris.base.EventListenerGroupStd;
import xyz.satdg.sao.icaris.base.SpMessageStd;
import xyz.satdg.sao.icaris.core.DbSystem;
import xyz.satdg.sao.icaris.database.Spreplytable;

/**
 * @author GongSunink
 */
public class SpreplyObserver extends ObserverBase {


    @Override
    public EventListenerGroupStd listenerStd() {
        return new EventListenerGroupStd(
                "特殊回复监听组", ObserverType.STANDARD);
    }

    @EventHandler(priority = Listener.EventPriority.LOW)
    public void spreplyListener(MessageEvent event) {
        // DbSystem.getTable("SpReplyTable").select(new SpMessageStd())
    }
}

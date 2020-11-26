package xyz.satdg.sao.icaris.core.observer.observers;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.event.events.FriendAddEvent;
import xyz.satdg.sao.icaris.api.EventListenerType;
import xyz.satdg.sao.icaris.api.bases.ObserverBase;
import xyz.satdg.sao.icaris.base.EventListenerGroupStd;

/**
 * @author GongSunink
 */
public class NewFriendRequestObserver extends ObserverBase {

    @Override
    public EventListenerGroupStd listenerStd() {
        return new EventListenerGroupStd("�º�������¼�������", EventListenerType.STANDARD);
    }

    @EventHandler(priority = Listener.EventPriority.HIGH)
    public void listener(FriendAddEvent event){

    }
}

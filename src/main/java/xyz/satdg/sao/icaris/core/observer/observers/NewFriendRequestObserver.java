package xyz.satdg.sao.icaris.core.observer.observers;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.event.events.FriendAddEvent;
import xyz.satdg.sao.icaris.api.bases.ObserverBase;
import xyz.satdg.sao.icaris.base.EventListenerType;
import xyz.satdg.sao.icaris.base.EventListenerGroupStd;

/**
 * @author GongSunink
 */
public class NewFriendRequestObserver extends ObserverBase {

    @Override
    public EventListenerGroupStd listenerStd() {
        return new EventListenerGroupStd("新好友添加事件监听组", EventListenerType.STANDARD);
    }

    @EventHandler(priority = Listener.EventPriority.HIGH)
    public void listener(FriendAddEvent event){

    }
}

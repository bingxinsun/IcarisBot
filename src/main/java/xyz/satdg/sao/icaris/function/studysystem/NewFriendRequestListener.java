package xyz.satdg.sao.icaris.function.studysystem;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.event.events.FriendAddEvent;
import xyz.satdg.sao.icaris.api.EventListenerType;
import xyz.satdg.sao.icaris.api.bases.EventListenerBase;
import xyz.satdg.sao.icaris.base.EventListenerGroupStd;

/**
 * @author GongSunink
 */
public class NewFriendRequestListener extends EventListenerBase {

    @Override
    public EventListenerGroupStd listenerStd() {
        return new EventListenerGroupStd("�º�������¼�������", EventListenerType.STANDARD);
    }

    @EventHandler(priority = Listener.EventPriority.HIGH)
    public void listener(FriendAddEvent event){

    }
}

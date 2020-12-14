package xyz.satdg.sao.icaris.core.observer.observers;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.message.GroupMessageEvent;
import xyz.satdg.sao.icaris.api.bases.ObserverBase;
import xyz.satdg.sao.icaris.base.EventListenerType;
import xyz.satdg.sao.icaris.base.EventListenerGroupStd;

/**
 * 复读机
 * @author GongSunink
 */
public class RepeatObserver extends ObserverBase {

    private int repeaterP = 70;

    @Override
    public EventListenerGroupStd listenerStd() {
        return new EventListenerGroupStd("复读监听组", EventListenerType.STANDARD);
    }


    @EventHandler(priority = Listener.EventPriority.LOW)
    public ListeningStatus RepaterEvent(GroupMessageEvent event){
//        if (BasicFunction.getRand(repeaterP,1)==1){
//            this.log(event.getBot());
//            event.getSubject().sendMessage(event.getMessage());
//        }
        return ListeningStatus.LISTENING;
    }

}

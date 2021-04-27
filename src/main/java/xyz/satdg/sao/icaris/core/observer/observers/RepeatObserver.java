package xyz.satdg.sao.icaris.core.observer.observers;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.EventPriority;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import xyz.satdg.sao.icaris.api.bases.ObserverBase;
import xyz.satdg.sao.icaris.base.EventListenerGroupStd;
import xyz.satdg.sao.icaris.core.function.BasicFunction;

/**
 * 复读机
 * @author GongSunink
 */
public class RepeatObserver extends ObserverBase {

    @Override
    public EventListenerGroupStd listenerStd() {
        return new EventListenerGroupStd(
                "复读监听组", ObserverType.STANDARD);
    }


    @EventHandler(priority = EventPriority.LOW)
    public ListeningStatus repeaterEvent(GroupMessageEvent event) {
        int repeaterP = 70;
        if (BasicFunction.getRand(repeaterP, 1) == 1) {
            this.log();
            event.getSubject().sendMessage(event.getMessage());
        }
        return ListeningStatus.LISTENING;
    }

}

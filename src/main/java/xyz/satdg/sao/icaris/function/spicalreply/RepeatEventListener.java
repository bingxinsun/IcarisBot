package xyz.satdg.sao.icaris.function.spicalreply;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.message.GroupMessageEvent;
import xyz.satdg.sao.icaris.api.EventListenerType;
import xyz.satdg.sao.icaris.api.bases.EventListenerBase;
import xyz.satdg.sao.icaris.base.EventListenerGroupStd;
import xyz.satdg.sao.icaris.function.BasicFunction;

/**
 * ������
 * @author GongSunink
 */
public class RepeatEventListener extends EventListenerBase {

    private int repeaterP = 70;

    @Override
    public EventListenerGroupStd listenerStd() {
        return new EventListenerGroupStd("����������", EventListenerType.STANDARD);
    }


    @EventHandler(priority = Listener.EventPriority.LOW)
    public ListeningStatus RepaterEvent(GroupMessageEvent event){
        if (BasicFunction.getRand(repeaterP,1)==1){
            this.log(event.getBot());
            event.getSubject().sendMessage(event.getMessage());
        }
        return ListeningStatus.LISTENING;
    }
}
package xyz.satdg.sao.icaris.function.spicalreply;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.message.MessageEvent;
import xyz.satdg.sao.icaris.api.EventListenerType;
import xyz.satdg.sao.icaris.api.bases.EventListenerBase;
import xyz.satdg.sao.icaris.base.EventListenerGroupStd;
import xyz.satdg.sao.icaris.database.SPreplyTable;

import java.util.Arrays;

/**
 * @author GongSunink
 */
public class SpreplyEventListener extends EventListenerBase {

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

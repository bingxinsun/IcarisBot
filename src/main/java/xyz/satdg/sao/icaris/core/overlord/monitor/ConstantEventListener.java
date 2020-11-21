package xyz.satdg.sao.icaris.core.overlord.monitor;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.message.GroupMessageEvent;
import net.mamoe.mirai.message.MessageEvent;
import xyz.satdg.sao.icaris.api.EventListenerType;
import xyz.satdg.sao.icaris.api.bases.EventListenerBase;
import xyz.satdg.sao.icaris.base.EventListenerGroupStd;

/**
 *�������������ȼ���ߣ����ܴ�ϻ���ȡ��
 * @author GongSunink
 */
public class ConstantEventListener extends EventListenerBase {

    @Override
    public EventListenerGroupStd listenerStd() {
        return new EventListenerGroupStd("��������", EventListenerType.CONSTANT);
    }

    @EventHandler(priority = Listener.EventPriority.HIGHEST)
    public void constantMessageListener(MessageEvent event){
        SaveMessages.save(event.getSender().getId(),event.getMessage().contentToString(),event.getSender().getNick(),(event instanceof GroupMessageEvent) ? ((GroupMessageEvent) event).getGroup().getName():"NULL",(event instanceof GroupMessageEvent) ? ((GroupMessageEvent) event).getGroup().getId():0);
    }
}

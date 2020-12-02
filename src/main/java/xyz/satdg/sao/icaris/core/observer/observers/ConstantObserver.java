package xyz.satdg.sao.icaris.core.observer.observers;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.message.GroupMessageEvent;
import net.mamoe.mirai.message.MessageEvent;
import xyz.satdg.sao.icaris.api.EventListenerType;
import xyz.satdg.sao.icaris.api.bases.ObserverBase;
import xyz.satdg.sao.icaris.base.EventListenerGroupStd;
import xyz.satdg.sao.icaris.core.observer.SaveMessages;

/**
 *�������������ȼ���ߣ����ܴ�ϻ���ȡ��
 * @author GongSunink
 */
public class ConstantObserver extends ObserverBase {

    @Override
    public EventListenerGroupStd listenerStd() {
        return new EventListenerGroupStd("��������", EventListenerType.CONSTANT);
    }

    @EventHandler(priority = Listener.EventPriority.HIGHEST)
    public void constantMessageListener(MessageEvent event){
        SaveMessages.save(event.getSender().getId(),event.getMessage().contentToString(),event.getSender().getNick(),(event instanceof GroupMessageEvent) ? ((GroupMessageEvent) event).getGroup().getName():"NULL",(event instanceof GroupMessageEvent) ? ((GroupMessageEvent) event).getGroup().getId():0);
    }
}
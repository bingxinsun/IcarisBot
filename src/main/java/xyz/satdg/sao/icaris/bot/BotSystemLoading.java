package xyz.satdg.sao.icaris.bot;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.BotOnlineEvent;

/**
 * ���ػ�����ϵͳ
 * ->�ڻ����˵�һ�ε�¼֮����м���
 * @author GongSunink
 */
public class BotSystemLoading extends SimpleListenerHost {

    @EventHandler(priority = Listener.EventPriority.HIGHEST)
    public ListeningStatus onBotFristLogin(BotOnlineEvent event){
        event.getBot().getLogger().info("�������״ε�¼��ɣ���ʼ����ϵͳ����");
        InitHelper.initWholeSystem(event.getBot());
        return ListeningStatus.STOPPED;
    }
}

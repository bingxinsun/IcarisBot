package xyz.satdg.sao.icaris.bot;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.BotOnlineEvent;
import xyz.satdg.sao.icaris.api.BotSystemLoader;
import xyz.satdg.sao.icaris.core.CommandSystem;
import xyz.satdg.sao.icaris.core.DbSystem;
import xyz.satdg.sao.icaris.core.ObserverSystem;

/**
 * ���ػ�����ϵͳ
 * ->�ڻ����˵�һ�ε�¼֮����м���
 * @author GongSunink
 */
public class IcarisLoader extends SimpleListenerHost implements BotSystemLoader {

    @EventHandler(priority = Listener.EventPriority.HIGHEST)
    public ListeningStatus onBotFristLogin(BotOnlineEvent event){
        event.getBot().getLogger().info("�������״ε�¼��ɣ���ʼ����ϵͳ����");
        DbSystem.jobStart();
        CommandSystem.jobStart();
        ObserverSystem.jobStart(event.getBot());
        return ListeningStatus.STOPPED;
    }
}

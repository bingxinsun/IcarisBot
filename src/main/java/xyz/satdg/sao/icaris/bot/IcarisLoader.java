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
 * 加载机器人系统
 * ->在机器人第一次登录之后进行加载
 * @author GongSunink
 */
public class IcarisLoader extends SimpleListenerHost implements BotSystemLoader {

    @EventHandler(priority = Listener.EventPriority.HIGHEST)
    public ListeningStatus onBotFristLogin(BotOnlineEvent event){
        event.getBot().getLogger().info("机器人首次登录完成，开始进行系统加载");
        DbSystem.jobStart();
        CommandSystem.jobStart();
        ObserverSystem.jobStart(event.getBot());
        return ListeningStatus.STOPPED;
    }
}

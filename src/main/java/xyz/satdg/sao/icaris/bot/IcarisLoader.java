package xyz.satdg.sao.icaris.bot;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.BotOnlineEvent;
import xyz.satdg.sao.icaris.api.Loader;
import xyz.satdg.sao.icaris.core.CommandSystem;
import xyz.satdg.sao.icaris.core.DbSystem;
import xyz.satdg.sao.icaris.core.ObserverSystem;

/**
 * 加载机器人系统
 * ->在机器人第一次登录之后进行加载
 * @author GongSunink
 */
public class IcarisLoader extends SimpleListenerHost implements Loader {

    @EventHandler(priority = Listener.EventPriority.HIGHEST)
    public ListeningStatus onBotFristLogin(BotOnlineEvent event){
        event.getBot().getLogger().info("initializing Icaris System pre-loading...");
        DbSystem.jobStart();
        CommandSystem.jobStart();
        ObserverSystem.jobStart(event.getBot());
        return ListeningStatus.STOPPED;
    }
}

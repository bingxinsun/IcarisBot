package xyz.satdg.sao.icaris.bot;

import net.mamoe.mirai.event.*;
import net.mamoe.mirai.event.events.BotOnlineEvent;
import xyz.satdg.sao.icaris.core.CommandSystem;
import xyz.satdg.sao.icaris.core.DbSystem;
import xyz.satdg.sao.icaris.core.IcarisBotSystem;
import xyz.satdg.sao.icaris.core.ObserverSystem;

import java.io.File;

import static xyz.satdg.sao.icaris.core.IcarisBotSystem.ICARIS_LOGGER;

/**
 * 加载机器人系统
 * ->在机器人第一次登录之后进行加载
 * @author GongSunink
 */
public class IcarisLoader extends SimpleListenerHost  {

    @EventHandler(priority = EventPriority.HIGHEST)
    public ListeningStatus onBotFirstLogin(BotOnlineEvent event){
        event.getBot().getLogger().info("initializing Icaris Systems pre-loading...");
        if (DbSystem.jobStart()){
            CommandSystem.jobStart();
            ObserverSystem.jobStart(event.getBot());
            return ListeningStatus.STOPPED;
        }
        ICARIS_LOGGER.error("Loading Failed");
        System.exit(-1);
        return ListeningStatus.STOPPED;
    }
}

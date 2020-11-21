package xyz.satdg.sao.icaris.bot;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.BotOnlineEvent;

/**
 * 加载机器人系统
 * ->在机器人第一次登录之后进行加载
 * @author GongSunink
 */
public class BotSystemLoading extends SimpleListenerHost {

    @EventHandler(priority = Listener.EventPriority.HIGHEST)
    public ListeningStatus onBotFristLogin(BotOnlineEvent event){
        event.getBot().getLogger().info("机器人首次登录完成，开始进行系统加载");
        InitHelper.initWholeSystem(event.getBot());
        return ListeningStatus.STOPPED;
    }
}

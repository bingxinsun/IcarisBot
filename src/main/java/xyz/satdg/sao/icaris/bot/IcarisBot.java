package xyz.satdg.sao.icaris.bot;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactoryJvm;
import net.mamoe.mirai.event.Events;
import net.mamoe.mirai.utils.BotConfiguration;
import xyz.satdg.sao.icaris.core.Mloger.MLoger;

import java.io.File;

/**
 * Icaris bot
 * @author GongSunink
 */
public class IcarisBot {
    /**
     * 机器人启动方法
     * @param qqId QQ账号
     * @param qqpWd QQ密码
     */
    public static void startBot(long qqId, String qqpWd) {
        BotConfiguration config = new BotConfiguration();
        Bot mBot;
        config.fileBasedDeviceInfo();
        mBot = BotFactoryJvm.newBot(qqId, qqpWd, config);
        config.setProtocol(BotConfiguration.MiraiProtocol.ANDROID_PHONE);
        config.setBotLoggerSupplier(bot -> new MLoger(mBot,"Icaris"));
        config.setNetworkLoggerSupplier(bot -> new MLoger(mBot,"Icaris"));
//      config.redirectNetworkLogToDirectory(new File("BotLog"));
//      config.redirectBotLogToDirectory(new File("BotLog"));
        Events.registerEvents(mBot,new BotSystemLoading());
        mBot.login();

        //尚未使用线程池
        new Thread(mBot::join).start();
        
    }
}

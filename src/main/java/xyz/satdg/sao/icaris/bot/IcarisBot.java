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
     * @param qqid QQ账号
     * @param qqpWd QQ密码
     */
    public static void startBot(long qqid, String qqpWd) {
        BotConfiguration config = new BotConfiguration();
        Bot mbot;
        config.fileBasedDeviceInfo();
        mbot = BotFactoryJvm.newBot(qqid, qqpWd, config);
        //config.setProtocol(BotConfiguration.MiraiProtocol.ANDROID_PHONE);
        config.setBotLoggerSupplier(bot -> new MLoger(mbot,"Icaris"));
        config.setNetworkLoggerSupplier(bot -> new MLoger(mbot,"Icaris"));
//      config.redirectNetworkLogToDirectory(new File("BotLog"));
//      config.redirectBotLogToDirectory(new File("BotLog"));
        Events.registerEvents(mbot,new BotSystemLoading());
        mbot.login();

        //尚未使用线程池
        new Thread(mbot::join).start();
        
    }
}

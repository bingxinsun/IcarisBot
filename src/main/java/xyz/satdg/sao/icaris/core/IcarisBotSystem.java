package xyz.satdg.sao.icaris.core;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;
import xyz.satdg.sao.icaris.bot.IcarisLoader;
import xyz.satdg.sao.icaris.core.loger.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * icaris bot system
 * @author GongSunink
 */
public final class IcarisBotSystem {


    public final static Logger ICARIS_LOGGER = new Logger("IcarisBot");

    public final static Properties ICARIS_PROPERTIES = new Properties();

    public static void start() throws IOException  {
        ICARIS_PROPERTIES.load(new FileReader("Icaris.properties"));
        creatBotAndLogin(Long.parseLong(ICARIS_PROPERTIES.getProperty("QQid")),
                ICARIS_PROPERTIES.getProperty("QQpwd"));
    }


    private static Bot creatBotAndLogin(long QQid, String QQpwd) {
        ICARIS_LOGGER.redirectLogToDir(new File("BotLog"));
//        try {
//            Class.forName("net.mamoe.mirai.internal.MiraiImpl");
//            ICARIS_LOGGER.info("LOADING net.mamoe.mirai.internal.MiraiImpl");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        Bot bot= BotFactory.INSTANCE.newBot(QQid, QQpwd,new BotConfiguration(){{
            setNetworkLoggerSupplier(bot -> ICARIS_LOGGER);
            setBotLoggerSupplier(bot -> ICARIS_LOGGER);
            setProtocol(MiraiProtocol.ANDROID_PHONE);
            fileBasedDeviceInfo("device.json");
        }});
        //Events.registerEvents(bot, (SimpleListenerHost) icarisSystem);
        bot.login();
        new Thread(()->{
            bot.join();
        }).start();
        return bot;
    }
}

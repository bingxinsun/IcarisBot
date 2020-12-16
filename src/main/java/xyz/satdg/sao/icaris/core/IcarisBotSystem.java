package xyz.satdg.sao.icaris.core;


import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoBasedClassDataFinder;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactoryJvm;
import net.mamoe.mirai.event.Events;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.utils.BotConfiguration;
import net.sf.json.JSONObject;
import org.omg.PortableInterceptor.INACTIVE;
import xyz.satdg.sao.icaris.api.Loader;
import xyz.satdg.sao.icaris.bot.IcarisLoader;
import xyz.satdg.sao.icaris.core.loger.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * icaris bot system
 * @author GongSunink
 */
@xyz.satdg.sao.icaris.api.marks.Loader(name = "IcarisSystem",type =
        xyz.satdg.sao.icaris.api.marks.Loader.LoaderType.SYSTEMLOADER)
public class IcarisBotSystem {

    public final static Logger ICARIS_LOGGER = new Logger("IcarisBot");

    public final static Properties ICARIS_PROPERTIES = new Properties();

    public static void start() throws IOException  {
        ICARIS_PROPERTIES.load(new FileReader("Icaris.properties"));
        creatBotAndLogin(Long.parseLong(ICARIS_PROPERTIES.getProperty("QQid")),
                ICARIS_PROPERTIES.getProperty("QQpwd"),new IcarisLoader());
    }

    private static Bot creatBotAndLogin(long QQid, String QQpwd, Loader loader){
        BotConfiguration configuration = new BotConfiguration();
        ICARIS_LOGGER.redirectLogToDir(new File("BotLog"));
        configuration.fileBasedDeviceInfo();
        configuration.setNetworkLoggerSupplier(bot-> ICARIS_LOGGER);
        configuration.setBotLoggerSupplier(bot-> ICARIS_LOGGER);
        Bot bot= BotFactoryJvm.newBot(QQid,QQpwd,configuration);
        Events.registerEvents(bot,(SimpleListenerHost)loader);
        bot.login();
        new Thread(()->{
            bot.join();
        }).start();
        return bot;
    }
}

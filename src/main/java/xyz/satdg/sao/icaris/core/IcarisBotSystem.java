package xyz.satdg.sao.icaris.core;


import kotlinx.serialization.json.JsonObject;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactoryJvm;
import net.mamoe.mirai.event.Events;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.utils.BotConfiguration;
import net.sf.json.JSONObject;
import xyz.satdg.sao.icaris.api.BotSystemLoader;
import xyz.satdg.sao.icaris.api.marks.SystemLoader;
import xyz.satdg.sao.icaris.bot.IcarisLoader;
import xyz.satdg.sao.icaris.core.Mloger.MLoger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * icaris bot system
 * @author GongSunink
 */
@SystemLoader(name = "IcarisSystem",type = SystemLoader.LoaderType.SYSTEMLOADER)
public class IcarisBotSystem {

    public static void start() throws IOException  {
        StringBuilder sb = new StringBuilder();
        String temp;
        BufferedReader reader = new BufferedReader(new FileReader("botConfig.json"));
        while ((temp=reader.readLine())!=null){
            sb.append(temp);
        }
        reader.close();
        JSONObject jsonObject = JSONObject.fromObject(sb.toString());
        creatBotAndLogin(jsonObject.getLong("QQid"),
                jsonObject.getString("QQpwd"),new IcarisLoader());
    }

    private static Bot creatBotAndLogin(long QQid, String QQpwd, BotSystemLoader loader){
        BotConfiguration configuration = new BotConfiguration();
        configuration.fileBasedDeviceInfo();
        configuration.setNetworkLoggerSupplier(bot-> new MLoger());
        configuration.setBotLoggerSupplier(bot-> new MLoger());
        Bot bot= BotFactoryJvm.newBot(QQid,QQpwd,configuration);
        Events.registerEvents(bot,(SimpleListenerHost)loader);
        bot.login();
        new Thread(()->{
            bot.join();
        }).start();
        return bot;
    }
}

package xyz.satdg.sao.icaris.bot;

import net.mamoe.mirai.Bot;
import xyz.satdg.sao.icaris.core.DbSystem;
import xyz.satdg.sao.icaris.core.Mloger.MLoger;

/**
 * @author GongSunink
 */
public class ShutDown {

    public final static void SHUTDOWNBOT(Bot bot){
        new MLoger().info("ϵͳ�ر���");
        DbSystem tableHelper = new DbSystem();
        bot.close(null);
        tableHelper.dumpDbSystem();
    }
}

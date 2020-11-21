package xyz.satdg.sao.icaris.bot;

import net.mamoe.mirai.Bot;
import xyz.satdg.sao.icaris.core.Mloger.MLoger;
import xyz.satdg.sao.icaris.database.TableHelper;

/**
 * @author GongSunink
 */
public class ShutDown {

    public final static void SHUTDOWNBOT(Bot bot){
        new MLoger().info("系统关闭中");
        TableHelper tableHelper = new TableHelper();
        bot.close(null);
        tableHelper.dumpDbSystem();
    }
}

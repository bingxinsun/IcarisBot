package xyz.satdg.sao.icaris.bot;

import net.mamoe.mirai.Bot;
import xyz.satdg.sao.icaris.core.DbSystem;
import xyz.satdg.sao.icaris.core.ObserverSystem;

import xyz.satdg.sao.icaris.core.CommandSystem;


/**
 * 系统初始化
 * 数据库系统->指令系统->命令系统
 * @author GongSunink
 */
public class InitHelper {

    public static void initWholeSystem(Bot bot){
        DbSystem.jobStart();
        CommandSystem.jobStart();
        ObserverSystem.jobStart(bot);
    }

}

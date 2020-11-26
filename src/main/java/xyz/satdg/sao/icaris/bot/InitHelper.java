package xyz.satdg.sao.icaris.bot;

import net.mamoe.mirai.Bot;
import xyz.satdg.sao.icaris.core.DbSystem;
import xyz.satdg.sao.icaris.core.observer.ConstantObserver;
import xyz.satdg.sao.icaris.core.ObserverSystem;
import xyz.satdg.sao.icaris.database.PlayerTable;
import xyz.satdg.sao.icaris.database.MessageTable;
import xyz.satdg.sao.icaris.database.SPreplyTable;
import xyz.satdg.sao.icaris.core.observer.observers.CommandObserver;
import xyz.satdg.sao.icaris.core.CommandSystem;
import xyz.satdg.sao.icaris.core.command.commandlmpl.universalcommand.CommandDebug;
import xyz.satdg.sao.icaris.core.command.commandlmpl.universalcommand.CommandHelp;
import xyz.satdg.sao.icaris.core.observer.observers.RepeatObserver;
import xyz.satdg.sao.icaris.core.observer.observers.SpreplyObserver;
import xyz.satdg.sao.icaris.core.observer.observers.DialogueStudyObserver;

/**
 * 系统初始化
 * 数据库系统->指令系统->命令系统
 * @author GongSunink
 */
public class InitHelper {

    public static void initWholeSystem(Bot bot){
        ObserverSystem observerSystem = new ObserverSystem();

        DbSystem.jobStart();
        CommandSystem.registCommands(new CommandHelp(),new CommandDebug());
        observerSystem.registObserver(new RepeatObserver(),new CommandObserver()
                ,new ConstantObserver(),new DialogueStudyObserver(),new SpreplyObserver());
        observerSystem.jobStart(bot);
    }

}

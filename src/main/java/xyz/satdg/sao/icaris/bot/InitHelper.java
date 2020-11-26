package xyz.satdg.sao.icaris.bot;

import net.mamoe.mirai.Bot;
import xyz.satdg.sao.icaris.core.observer.ConstantEventListener;
import xyz.satdg.sao.icaris.database.PlayerTable;
import xyz.satdg.sao.icaris.database.TableHelper;
import xyz.satdg.sao.icaris.database.MessageTable;
import xyz.satdg.sao.icaris.database.SPreplyTable;
import xyz.satdg.sao.icaris.function.EventListenerHelper;
import xyz.satdg.sao.icaris.function.commands.CommandEventListener;
import xyz.satdg.sao.icaris.function.commands.CommandHelper;
import xyz.satdg.sao.icaris.function.commands.commandlmpl.universalcommand.CommandDebug;
import xyz.satdg.sao.icaris.function.commands.commandlmpl.universalcommand.CommandHelp;
import xyz.satdg.sao.icaris.function.spicalreply.RepeatEventListener;
import xyz.satdg.sao.icaris.function.spicalreply.SpreplyEventListener;
import xyz.satdg.sao.icaris.function.studysystem.DialogueStudyListener;

/**
 * ϵͳ��ʼ��
 * ���ݿ�ϵͳ->ָ��ϵͳ->����ϵͳ
 * @author GongSunink
 */
public class InitHelper {

    public static void initWholeSystem(Bot bot){
        TableHelper tableHelper = new TableHelper("BotDB");
        EventListenerHelper eventListenerHelper = new EventListenerHelper();

        tableHelper.RegistTables(new MessageTable(),new SPreplyTable(),new PlayerTable());
        tableHelper.dbSystemInit();

        CommandHelper.registCommand(new CommandHelp(),new CommandDebug());

        eventListenerHelper.registEventListener(new RepeatEventListener(),new CommandEventListener()
                ,new ConstantEventListener(),new DialogueStudyListener(),new SpreplyEventListener());
        eventListenerHelper.JobStart(bot);
    }

}

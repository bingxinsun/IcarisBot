package xyz.satdg.sao.icaris.function.commands.commandlmpl.universalcommand;

import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Message;
import org.jetbrains.annotations.NotNull;
import xyz.satdg.sao.icaris.api.bases.CommandBase;
import xyz.satdg.sao.icaris.base.CommandStd;

import java.util.List;

/**
 * @author GongSunink
 */
public class CommandDebug extends CommandBase {

    @Override
    public CommandStd command() {
        return new CommandStd("debug命令","debug",1);
    }

    @Override
    public boolean excute(Message message, List<String> args, User sender, @NotNull Contact Subject) {
        //Subject.sendMessage("当前时间->\"+new Date().toString()+\"\\n\"+\"机器人实例->\"+Subject.getBot().toString()");
        return true;
    }
}

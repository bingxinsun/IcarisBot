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
        return new CommandStd("debug����","debug",1);
    }

    @Override
    public boolean excute(Message message, List<String> args, User sender, @NotNull Contact Subject) {
        //Subject.sendMessage("��ǰʱ��->\"+new Date().toString()+\"\\n\"+\"������ʵ��->\"+Subject.getBot().toString()");
        return true;
    }
}

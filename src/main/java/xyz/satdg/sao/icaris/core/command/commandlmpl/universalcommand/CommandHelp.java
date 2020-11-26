package xyz.satdg.sao.icaris.core.command.commandlmpl.universalcommand;

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
public class CommandHelp extends CommandBase {

    @Override
    public CommandStd command() {
        return new CommandStd("帮助指令","help");
    }

    @Override
    public boolean excute(Message message, List<String> args, User sender, @NotNull Contact Subject) {
        Subject.sendMessage("暂时还没有帮助哦");
        return true;
    }
}

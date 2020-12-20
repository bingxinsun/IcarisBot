package xyz.satdg.sao.icaris.api;

import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Message;
import org.jetbrains.annotations.NotNull;
import xyz.satdg.sao.icaris.base.CommandStd;

import java.util.List;

/**
 * @author GongSunink
 */
public interface Command {

    /**
     * 命令实例
     * @return 命令实例
     */
    CommandStd command();
    /**
     * 指令执行逻辑
     * @param message 消息
     * @param args 指令参数
     * @param sender 发送者
     * @param subject 事件主体
     * @return 执行结果
     */
    boolean execute(Message message, List<String> args, User sender, @NotNull Contact subject);
}

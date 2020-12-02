package xyz.satdg.sao.icaris.api;

import net.mamoe.mirai.Bot;
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
     * ����ʵ��
     * @return ����ʵ��
     */
    CommandStd command();
    /**
     * ָ��ִ���߼�
     * @param message ��Ϣ
     * @param args ָ�����
     * @param sender ������
     * @param subject �¼�����
     * @return ִ�н��
     */
    boolean execute(Message message, List<String> args, User sender, @NotNull Contact subject);
}

package xyz.satdg.sao.icaris.function.commands;

import xyz.satdg.sao.icaris.api.Command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * ָ�����
 * @author GongSunink
 */
public class CommandHelper   {

    private static HashMap<String, Command> commandMap =new HashMap<>();

    public static void registCommand(Command ...commands){
        for (Command command: commands) {
            CommandHelper.commandMap.put(command.command().getCommandHead(),command);
        }
    }
    /**
     * ͨ����Ϣ���ָ�����Args
     * @param message ��Ϣ
     * @return ָ������б�
     */
    public static List<String> getArgs(String message){
        String[] messagesp=message.split(" ");
        List<String> args=new ArrayList<>();
        if (messagesp.length==0){
            return new ArrayList<>();
        }
        Collections.addAll(args,messagesp);
        return args;
    }
    /**
     * 1.����ո�
     * 2.�ж��Ƿ����ָ��
     * 3.��������зָ�
     * 4.�жϷָ�Ĳ����ǲ��ǰ�����commandlist�У���һ���ǰ���ָ��ָ���Ҳ��ֵĵ�һ����
     * @param message ��Ϣ
     * @return ָ����󣬲����ڸ�ָ��ʱ����null
     */
    public static Command getCommand(String message){
        message.trim();
        if (message.contains("-")) {
            message = message.split("-")[1];
            if (commandMap.containsKey(message)) {
                return commandMap.get(message);
            }
        }
        else if (message.contains(".")) {
            message = message.split("\\.")[1];
            //ע��ת���
            if (commandMap.containsKey(message)) {
                return commandMap.get(message);
            }
        }
        else if (message.contains("!")) {
            message = message.split("!")[1];
            if (commandMap.containsKey(message)) {
                return commandMap.get(message);
            }
        }
        return null;
    }
}
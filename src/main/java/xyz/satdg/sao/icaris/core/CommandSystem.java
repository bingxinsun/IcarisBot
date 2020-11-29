package xyz.satdg.sao.icaris.core;

import xyz.satdg.sao.icaris.api.Command;
import xyz.satdg.sao.icaris.core.Mloger.MLoger;
import xyz.satdg.sao.icaris.core.command.commandlmpl.CommandDebug;
import xyz.satdg.sao.icaris.core.command.commandlmpl.CommandHelp;

import java.util.*;

/**
 * ָ�����
 * @author GongSunink
 */
public class CommandSystem {

    private static HashMap<String,Command> commandMap =new HashMap<>();

    public static void jobStart(){
        MLoger.getLoger().info("���ڽ���ָ��ϵͳ�Զ�����");
        Set<Class<?>> classSet = null;
        try {
            classSet = ClassScanner.scanPackage("xyz.satdg.sao.icaris.core.command.commandlmpl");
        }catch (Exception e){
            MLoger.getLoger().error(e.getMessage());
        }
        if (classSet!=null&&!classSet.isEmpty()){
            for (Class c : classSet){
                try {
                    if (c.newInstance() instanceof Command){
                        commandMap.put(((Command)c.newInstance()).command().getCommandHead(),(Command)c.newInstance());
                    }
                }catch (Exception e){
                    MLoger.getLoger().error("ָ���Զ�����ʧ��,���ڽ����ֶ�����",e.getCause());
                    initByManual(new CommandHelp(),new CommandDebug());
                }
            }
        }else {
            MLoger.getLoger().error("ָ���Զ�����ʧ��,���ڽ����ֶ�����");
            initByManual(new CommandHelp(),new CommandDebug());
        }
        MLoger.getLoger().info("ָ��ϵͳ�Զ��������!");
    }

    private static void initByManual(Command ...commands) {
        for (int i = 0; i < commands.length; i++) {
            registCommands(commands);
        }
    }


    private static void registCommands(Command ...commands){
        for (Command command: commands) {
            CommandSystem.commandMap.put(command.command().getCommandHead(),command);
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

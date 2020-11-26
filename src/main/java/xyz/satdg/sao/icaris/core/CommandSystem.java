package xyz.satdg.sao.icaris.core;

import xyz.satdg.sao.icaris.api.Command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 指令工具类
 * @author GongSunink
 */
public class CommandSystem {

    private static HashMap<String, Command> commandMap =new HashMap<>();

    public static void registCommands(Command ...commands){
        for (Command command: commands) {
            CommandSystem.commandMap.put(command.command().getCommandHead(),command);
        }
    }
    /**
     * 通过消息获得指令参数Args
     * @param message 消息
     * @return 指令参数列表
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
     * 1.清除空格
     * 2.判断是否包含指标
     * 3.包含则进行分割
     * 4.判断分割的部分是不是包含在commandlist中，且一定是按照指标分割的右部分的第一部分
     * @param message 消息
     * @return 指令对象，不存在该指令时返回null
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
            //注意转义符
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

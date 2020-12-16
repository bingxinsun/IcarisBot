package xyz.satdg.sao.icaris.core;

import xyz.satdg.sao.icaris.api.Command;
import xyz.satdg.sao.icaris.core.command.commandlmpl.CommandDebug;
import xyz.satdg.sao.icaris.core.command.commandlmpl.CommandHelp;
import xyz.satdg.sao.icaris.core.loger.exception.PackageScannerException;

import java.io.IOException;
import java.util.*;

import static xyz.satdg.sao.icaris.core.IcarisBotSystem.ICARIS_LOGGER;

/**
 * 指令工具类
 * @author GongSunink
 */
public class CommandSystem {

    private static HashMap<String,Command> commandMap =new HashMap<>();

    public static void jobStart(){
        ICARIS_LOGGER.info("正在进行指令系统自动挂载");
        Set<Class<?>> classSet = null;
        try {
            classSet = ClassScanner.scanSinglePackage(
                    "xyz.satdg.sao.icaris.core.command.commandlmpl");
        }catch (ClassNotFoundException | IOException e){
            ICARIS_LOGGER.error(new PackageScannerException(e));
        }
        if (classSet!=null&&!classSet.isEmpty()){
            for (Class c : classSet){
                try {
                    if (c.newInstance() instanceof Command){
                        commandMap.put(((Command)c.newInstance()).command().getCommandHead(),(Command)c.newInstance());
                    }
                }catch (InstantiationException | IllegalAccessException e){
                    ICARIS_LOGGER.error("指令自动挂载失败,正在进行手动挂载",e);
                    initByManual(new CommandHelp(),new CommandDebug());
                }
            }
        }else {
            ICARIS_LOGGER.error("指令自动挂载失败,正在进行手动挂载");
            initByManual(new CommandHelp(),new CommandDebug());
        }
        ICARIS_LOGGER.info("指令系统自动挂载完成!");
    }

    private static void initByManual(Command ...commands) {
        registCommands(commands);
    }

    private static void registCommands(Command ...commands){
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
        String[] messageSp=message.split(" ");
        List<String> args=new ArrayList<>();
        if (messageSp.length==0){
            return new ArrayList<>();
        }
        Collections.addAll(args,messageSp);
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

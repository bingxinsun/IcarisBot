package xyz.satdg.sao.icaris.base;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 指令对象
 * @author GongSunink
 */
public class CommandStd {

    private String commandName;
    private String commandHead;
    private ArrayList<String> commandAlias;
    private int commandPermissionLevel;

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public void setCommandHead(String commandHead) {
        this.commandHead = commandHead;
    }

    public void setCommandPermissionLevel(int commandPermissionLevel) {
        this.commandPermissionLevel = commandPermissionLevel;
    }

    public int getCommandPermissionLevel() {
        return this.commandPermissionLevel;
    }
    public String getCommandHead() {
        return this.commandHead;
    }

    @Override
    public String toString() {
        return "CommandStd{" +
                "commandName='" + commandName + '\'' +
                ", commandHead='" + commandHead + '\'' +
                ", commandAlias=" + commandAlias +
                ", commandPermissionLevel=" + commandPermissionLevel +
                '}';
    }

    public String getCommandName() {
        return this.commandName;
    }
    public ArrayList<String> getCommandAlias() {
        return this.commandAlias;
    }
    public void setCommandAlias(ArrayList<String> commandAlias) {
        this.commandAlias = commandAlias;
    }

    public CommandStd(String commandName, String commandHead, ArrayList<String> commandAlias,int commandPermissionLevel){
        this.commandAlias=commandAlias;
        this.commandHead=commandHead;
        this.commandName=commandName;
        this.commandPermissionLevel=commandPermissionLevel;
    }
    public CommandStd(String commandName,String commandHead,int commandPermissionLevel){
        this(commandName,commandHead,new ArrayList<>(),commandPermissionLevel);
    }
    public CommandStd(String commandName,String commandHead){
        this(commandName,commandHead,0);
    }

    public void addAlias(String ...alias){
        Collections.addAll(this.commandAlias,alias);
    }

}

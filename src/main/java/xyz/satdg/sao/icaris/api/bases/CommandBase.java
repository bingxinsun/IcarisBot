package xyz.satdg.sao.icaris.api.bases;

import xyz.satdg.sao.icaris.api.Command;

import static xyz.satdg.sao.icaris.core.IcarisBotSystem.ICARIS_LOGGER;

/**
 * Command系统基础
 * @author GongSunink
 */
public abstract class CommandBase implements Command {

    public void log(){
        ICARIS_LOGGER.info("指令触发<"+this.command().getCommandName()+">");
    }
    @Override
    public String toString() {
        return this.command().getCommandName();
    }
    @Override
    public int hashCode() {
        return this.command().getCommandName().hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CommandBase){
            return this.command().getCommandName().equals(obj);
        }
        return false;
    }
}

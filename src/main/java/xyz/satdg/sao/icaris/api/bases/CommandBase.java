package xyz.satdg.sao.icaris.api.bases;

import net.mamoe.mirai.Bot;
import xyz.satdg.sao.icaris.api.Command;
import xyz.satdg.sao.icaris.core.Mloger.MLoger;

/**
 * Commandϵͳ����
 * @author GongSunink
 */
public abstract class CommandBase implements Command {

    public void log(){
        MLoger.getLoger().info("ָ���<"+this.command().getCommandName()+">");
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

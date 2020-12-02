package xyz.satdg.sao.icaris.core.observer.observers;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.message.FriendMessageEvent;
import net.mamoe.mirai.message.GroupMessageEvent;
import net.mamoe.mirai.message.MessageEvent;
import net.mamoe.mirai.message.TempMessageEvent;
import xyz.satdg.sao.icaris.api.Command;
import xyz.satdg.sao.icaris.api.EventListenerType;
import xyz.satdg.sao.icaris.api.bases.ObserverBase;
import xyz.satdg.sao.icaris.base.EventListenerGroupStd;
import xyz.satdg.sao.icaris.core.CommandSystem;

/**
 * 命令事件监听器
 * @author GongSunink
 */
public class CommandObserver extends ObserverBase {


    @Override
    public EventListenerGroupStd listenerStd() {
        return new EventListenerGroupStd("指令监听器", EventListenerType.STANDARD);
    }


    /**
     * 处理命令
     * @param event 事件主体
     */
    private void dueCommands(MessageEvent event){
//        Command command= CommandSystem.getCommand(event.getMessage().contentToString());
//        if (command!=null) {
//            this.log(event.getBot());
//            command.log(event.getBot());
//            command.execute(event.getMessage(), CommandSystem.getArgs(event.getMessage().contentToString()), event.getSender(), event.getSubject());
//            event.intercept();
//        }

    }

    @EventHandler(priority = Listener.EventPriority.HIGH)
    public ListeningStatus messageListener(MessageEvent event){
        dueCommands(event);
        return ListeningStatus.LISTENING;
    }

    @EventHandler(priority = Listener.EventPriority.HIGH)
    public ListeningStatus groupListener(GroupMessageEvent event){
        dueCommands(event);
        return ListeningStatus.LISTENING;
    }

    @EventHandler(priority = Listener.EventPriority.HIGH)
    public ListeningStatus friendListener(FriendMessageEvent event){
        dueCommands(event);
        return ListeningStatus.LISTENING;
    }

    @EventHandler(priority = Listener.EventPriority.HIGH)
    public ListeningStatus tempListener(TempMessageEvent event){
        dueCommands(event);
        return ListeningStatus.LISTENING;
    }
}

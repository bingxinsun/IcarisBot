package xyz.satdg.sao.icaris.core.observer.observers;


import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.message.GroupMessageEvent;
import net.mamoe.mirai.message.MessageEvent;
import xyz.satdg.sao.icaris.api.EventListenerType;
import xyz.satdg.sao.icaris.api.bases.ObserverBase;
import xyz.satdg.sao.icaris.base.EventListenerGroupStd;
import xyz.satdg.sao.icaris.base.SpMessageStd;
import xyz.satdg.sao.icaris.database.SPreplyTable;


/**
 * 对话学习事件监听器
 * @author GongSunink
 */
public class DialogueStudyObserver extends ObserverBase {

    private int studySequence=-1;
    private User user;
    SPreplyTable sPreplyTable= new SPreplyTable();
    String messageReq;

    @Override
    public EventListenerGroupStd listenerStd() {
        return new EventListenerGroupStd("对话学习监听组", EventListenerType.STANDARD);
    }

    /**
     * 对话学习系统监听器，不支持多人异步处理
     * 当监听到信息"对话学习"时
     * <-message
     * ->message
     * <-reply
     * ->return
     * @param event 消息时间
     * @return
     */
    @EventHandler(priority = Listener.EventPriority.HIGH)
    public ListeningStatus dialogueStudyListener(MessageEvent event){
//        if ("对话学习".equals(event.getMessage().contentToString())&&studySequence==-1){
//            user=event.getSender();
//            studySequence++;
//            return ListeningStatus.LISTENING;
//        }else if (studySequence==0&&event.getSender().getId()==(user.getId())){
//            messageReq=event.getMessage().contentToString();
//            event.getSubject().sendMessage("如何回复以上对话");
//            studySequence++;
//            return ListeningStatus.LISTENING;
//        }else if (studySequence==1&&event.getSender().getId()==(user.getId())){
//            event.getSubject().sendMessage("明白");
//        }
//        if (studySequence==1){
//            studySequence=-1;
//            sPreplyTable.insert(new SpMessageStd(event.getSender().getId(),this.messageReq,event.getMessage().contentToString()
//                    ,(event instanceof  GroupMessageEvent ?
//                            ((GroupMessageEvent) event).getGroup().getName() : null),
//                    (event instanceof  GroupMessageEvent ?
//                            ((GroupMessageEvent) event).getGroup().getId() : Integer.MIN_VALUE),
//                    event.getSender().getNick()));
//            event.intercept();
//        }
        return ListeningStatus.LISTENING;
    }
}
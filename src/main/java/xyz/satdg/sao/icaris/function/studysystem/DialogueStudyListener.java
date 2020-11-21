package xyz.satdg.sao.icaris.function.studysystem;


import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.message.GroupMessageEvent;
import net.mamoe.mirai.message.MessageEvent;
import xyz.satdg.sao.icaris.api.EventListenerType;
import xyz.satdg.sao.icaris.api.bases.EventListenerBase;
import xyz.satdg.sao.icaris.base.EventListenerGroupStd;
import xyz.satdg.sao.icaris.database.SPreplyTable;


/**
 * �Ի�ѧϰ�¼�������
 * @author GongSunink
 */
public class DialogueStudyListener extends EventListenerBase {

    private int studySequence=-1;
    private User user;
    SPreplyTable sPreplyTable= new SPreplyTable();
    String messageReq;

    @Override
    public EventListenerGroupStd listenerStd() {
        return new EventListenerGroupStd("�Ի�ѧϰ������", EventListenerType.STANDARD);
    }

    /**
     * �Ի�ѧϰϵͳ����������֧�ֶ����첽����
     * ����������Ϣ"����ħ��ɳ"ʱ
     * ->�����Ҫѧʲô�Ի�daze~?
     * <-message
     * ->�����������,��Ӧ�������ش���daze~
     * <-return
     * ->�õ�,��������daze~
     * @param event
     * @return
     */
    @EventHandler(priority = Listener.EventPriority.HIGH)
    public ListeningStatus dialogueStudyListener(MessageEvent event){
        if ("�Ի�ѧϰ".equals(event.getMessage().contentToString())&&studySequence==-1){
            user=event.getSender();
            event.getSubject().sendMessage("ѧϰģʽ�ѿ���");
            studySequence++;
            return ListeningStatus.LISTENING;
        }else if (studySequence==0&&event.getSender().getId()==(user.getId())){
            messageReq=event.getMessage().contentToString();
            event.getSubject().sendMessage("��λظ�");
            studySequence++;
            return ListeningStatus.LISTENING;
        }else if (studySequence==1&&event.getSender().getId()==(user.getId())){
            event.getSubject().sendMessage("����");
        }
        if (studySequence==1){
            studySequence=-1;
            sPreplyTable.insert(event.getSender().getId(),this.messageReq,event.getMessage().contentToString()
                    ,(event instanceof  GroupMessageEvent ? ((GroupMessageEvent) event).getGroup().getName() : null),
                    (event instanceof  GroupMessageEvent ? ((GroupMessageEvent) event).getGroup().getId() : Integer.MIN_VALUE),
                    event.getSender().getNick());
            event.intercept();
        }
        return ListeningStatus.LISTENING;
    }
}
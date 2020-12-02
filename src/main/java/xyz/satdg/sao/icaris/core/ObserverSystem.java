package xyz.satdg.sao.icaris.core;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.Events;
import net.mamoe.mirai.event.SimpleListenerHost;
import xyz.satdg.sao.icaris.core.Mloger.MLoger;
import xyz.satdg.sao.icaris.core.observer.observers.ConstantObserver;
import xyz.satdg.sao.icaris.core.observer.observers.CommandObserver;
import xyz.satdg.sao.icaris.core.observer.observers.DialogueStudyObserver;
import xyz.satdg.sao.icaris.core.observer.observers.RepeatObserver;
import xyz.satdg.sao.icaris.core.observer.observers.SpreplyObserver;

import java.util.HashSet;
import java.util.Set;

/**
 * @author GongSunink
 */
public class ObserverSystem {

    private static Set<SimpleListenerHost> observerSet = new HashSet<>();

    public static Set<SimpleListenerHost> getObserverSet(){
        return observerSet;
    }

    public static void jobStart(Bot botIn){
        MLoger.getLoger().info("���ڽ��ж���ϵͳ�Զ�����");
        Set<Class<?>> classSet = null;
        try {
            classSet = ClassScanner.scanPackage("xyz.satdg.sao.icaris.core.observer.observers");
        }catch (Exception e){
            MLoger.getLoger().error(e.getMessage());
        }
        if (classSet!=null&&!classSet.isEmpty()){
            for (Class c : classSet){
                try {
                    if (c.newInstance() instanceof SimpleListenerHost){
                        observerSet.add((SimpleListenerHost) c.newInstance());
                    }
                }catch (Exception e){
                    MLoger.getLoger().error("����ϵͳ�Զ�����ʧ��,���ڽ����ֶ�����",e.getCause());
                    initByManual(botIn,new RepeatObserver(),new CommandObserver()
                            ,new ConstantObserver(),new DialogueStudyObserver(),new SpreplyObserver());
                }
            }
        }else {
            MLoger.getLoger().error("����ϵͳ�Զ�����ʧ��,���ڽ����ֶ�����");
            initByManual(botIn,new RepeatObserver(),new CommandObserver()
                    ,new ConstantObserver(),new DialogueStudyObserver(),new SpreplyObserver());
        }
        for (SimpleListenerHost observer : observerSet){
            Events.registerEvents(botIn,observer);
        }

        MLoger.getLoger().info("����ϵͳ�Զ��������!");
    }
    private static void initByManual(Bot bot,SimpleListenerHost ...observers){
        for (SimpleListenerHost observer : observers){
            Events.registerEvents(bot,observer);
        }

    }

}
package xyz.satdg.sao.icaris.core;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.Events;
import net.mamoe.mirai.event.SimpleListenerHost;
import xyz.satdg.sao.icaris.core.observer.observers.ConstantObserver;
import xyz.satdg.sao.icaris.core.observer.observers.CommandObserver;
import xyz.satdg.sao.icaris.core.observer.observers.DialogueStudyObserver;
import xyz.satdg.sao.icaris.core.observer.observers.RepeatObserver;
import xyz.satdg.sao.icaris.core.observer.observers.SpreplyObserver;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import static xyz.satdg.sao.icaris.core.IcarisBotSystem.ICARIS_LOGGER;
/**
 * @author GongSunink
 */
public final class ObserverSystem {

    private static Set<SimpleListenerHost> observerSet = new HashSet<>();

    public static Set<SimpleListenerHost> getObserverSet(){
        return observerSet;
    }

    public static void jobStart(Bot botIn){
        ICARIS_LOGGER.info("正在进行叮当系统自动挂载");
        Set<Class<?>> classSet = null;
        try {
            classSet = ClassScanner.scanSinglePackage("xyz.satdg.sao.icaris.core.observer.observers");
        }catch (ClassNotFoundException | IOException e){
            ICARIS_LOGGER.error("Package scanner error"+e);
        }
        if (classSet!=null&&!classSet.isEmpty()){
            for (Class c : classSet){
                try {
                    if (c.newInstance() instanceof SimpleListenerHost){
                        observerSet.add((SimpleListenerHost) c.newInstance());
                    }
                }catch (InstantiationException|IllegalAccessException e){
                    ICARIS_LOGGER.error("叮当系统自动挂载失败,正在进行手动挂载",e);
                    initByManual(botIn,new RepeatObserver(),new CommandObserver()
                            ,new ConstantObserver(),new DialogueStudyObserver(),new SpreplyObserver());
                }
            }
        }else {
            ICARIS_LOGGER.error("叮当系统自动挂载失败,正在进行手动挂载");
            initByManual(botIn,new RepeatObserver(),new CommandObserver()
                    ,new ConstantObserver(),new DialogueStudyObserver(),new SpreplyObserver());
        }
        for (SimpleListenerHost observer : observerSet){
            Events.registerEvents(botIn,observer);
        }

        ICARIS_LOGGER.info("叮当系统自动挂载完成!");
    }
    private static void initByManual(Bot bot,SimpleListenerHost ...observers){
        for (SimpleListenerHost observer : observers){
            Events.registerEvents(bot,observer);
        }

    }

}
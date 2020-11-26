package xyz.satdg.sao.icaris.core.observer;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.Events;
import net.mamoe.mirai.event.SimpleListenerHost;
import xyz.satdg.sao.icaris.api.bases.ObserverBase;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author GongSunink
 */
public class ObserverSystem {

    private static Set<SimpleListenerHost> ObserverSet = new HashSet<>();

    public static Set<SimpleListenerHost> getObserverSet(){
        return ObserverSet;
    }

    public void registObserver(ObserverBase...events){
        Collections.addAll(ObserverSet,events);
    }

    /**
     * 批量注册
     * @param bot 事件对应的机器人
     */
    public void jobStart(Bot bot){
        for (SimpleListenerHost simpleListenerHost: ObserverSet){
            Events.registerEvents(bot,simpleListenerHost);
        }
    }
}
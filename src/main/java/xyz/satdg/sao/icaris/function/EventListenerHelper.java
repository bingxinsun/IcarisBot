package xyz.satdg.sao.icaris.function;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.Events;
import net.mamoe.mirai.event.SimpleListenerHost;
import xyz.satdg.sao.icaris.api.bases.EventListenerBase;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author GongSunink
 */
public class EventListenerHelper {

    private static Set<SimpleListenerHost> eventListenerSet = new HashSet<>();

    public static Set<SimpleListenerHost> getEventListenerSet(){
        return eventListenerSet;
    }

    public void registEventListener(EventListenerBase ...events){
        Collections.addAll(eventListenerSet,events);
    }

    /**
     * ����ע��
     * @param bot �¼���Ӧ�Ļ�����
     */
    public void JobStart(Bot bot){
        for (SimpleListenerHost simpleListenerHost: eventListenerSet){
            Events.registerEvents(bot,simpleListenerHost);
        }
    }
}
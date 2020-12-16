package xyz.satdg.sao.icaris.base;

import xyz.satdg.sao.icaris.api.Observer;

/**
 * 事件监听组实例
 * @author GongSunink
 */
public class EventListenerGroupStd {

    private String listenerGroupName;
    private Observer.ObserverType eventListenerType;

    public Observer.ObserverType getEventListenerType() {
        return eventListenerType;
    }

    public String getListenerGroupName() {
        return listenerGroupName;
    }

    public void setListenerGroupName(String listenerGroupName) {
        this.listenerGroupName = listenerGroupName;
    }

    @Override
    public String toString() {
        return "EventListenerGroupStd{" +
                "listenerGroupName='" + listenerGroupName + '\'' +
                ", eventListenerType=" + eventListenerType +
                '}';
    }

    public EventListenerGroupStd(String listenerGroupName, Observer.ObserverType eventListenerType) {
        this.listenerGroupName = listenerGroupName;
        this.eventListenerType = eventListenerType;
    }

    public EventListenerGroupStd(String listenerGroupName) {
        this.listenerGroupName = listenerGroupName;
    }
}

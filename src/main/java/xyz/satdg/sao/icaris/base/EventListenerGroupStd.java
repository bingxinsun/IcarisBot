package xyz.satdg.sao.icaris.base;

import xyz.satdg.sao.icaris.api.EventListenerType;

/**
 * 事件监听组实例
 * @author GongSunink
 */
public class EventListenerGroupStd {

    private String listenerGroupName;
    private EventListenerType eventListenerType;

    public EventListenerType getEventListenerType() {
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

    public void setEventListenerType(EventListenerType eventListenerType) {
        this.eventListenerType = eventListenerType;
    }

    public EventListenerGroupStd(String listenerGroupName, EventListenerType eventListenerType){
        this.eventListenerType=eventListenerType;
        this.listenerGroupName =listenerGroupName;
    }
}

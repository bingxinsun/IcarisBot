package xyz.satdg.sao.icaris.api;

import net.mamoe.mirai.Bot;
import xyz.satdg.sao.icaris.base.EventListenerGroupStd;

/**
 * �¼��ӿ�
 * @author GongSunink
 */
public interface EventListener {
    /**
     * ��־��¼
     * @param bot ������ʵ��
     */
    void log(Bot bot);
    /**
     * �¼�ʵ����ÿ���¼�����Ҫ�д˶���
     * @return �¼�ʵ��
     */
    EventListenerGroupStd listenerStd();
}

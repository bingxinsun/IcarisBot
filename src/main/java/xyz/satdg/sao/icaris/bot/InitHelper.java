package xyz.satdg.sao.icaris.bot;

import net.mamoe.mirai.Bot;
import xyz.satdg.sao.icaris.core.DbSystem;
import xyz.satdg.sao.icaris.core.ObserverSystem;

import xyz.satdg.sao.icaris.core.CommandSystem;


/**
 * ϵͳ��ʼ��
 * ���ݿ�ϵͳ->ָ��ϵͳ->����ϵͳ
 * @author GongSunink
 */
public class InitHelper {

    public static void initWholeSystem(Bot bot){
        DbSystem.jobStart();
        CommandSystem.jobStart();
        ObserverSystem.jobStart(bot);
    }

}

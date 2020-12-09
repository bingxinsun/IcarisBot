package xyz.satdg.sao.icaris.core.observer;

import xyz.satdg.sao.icaris.api.bases.TableBase;
import xyz.satdg.sao.icaris.base.MessageStd;
import xyz.satdg.sao.icaris.core.DbSystem;
import xyz.satdg.sao.icaris.database.MessageTable;


/**
 * 消息保存
 * @author GongSunink
 */
public class SaveMessages {


    @SuppressWarnings("unchecked")
    public static void save(long id, String message, String author,String groupName,long groupId){
        DbSystem.getTable("MessageTable")
                .insert(new MessageStd(id,message,author,groupName,groupId));
    }

}

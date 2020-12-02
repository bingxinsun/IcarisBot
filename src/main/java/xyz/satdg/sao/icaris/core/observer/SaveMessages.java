package xyz.satdg.sao.icaris.core.observer;

import xyz.satdg.sao.icaris.api.bases.TableBase;
import xyz.satdg.sao.icaris.base.MessageStd;
import xyz.satdg.sao.icaris.core.DbSystem;
import xyz.satdg.sao.icaris.database.MessageTable;


/**
 * ÏûÏ¢±£´æ
 * @author GongSunink
 */
public class SaveMessages {


    public static void save(long id, String message, String author,String groupName,long groupId){
        DbSystem.getTable("MESSAGETABLE")
                .insert(new MessageStd(id,message,author,groupName,groupId));
    }

}

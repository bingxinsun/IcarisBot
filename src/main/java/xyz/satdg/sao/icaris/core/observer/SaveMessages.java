package xyz.satdg.sao.icaris.core.observer;

import xyz.satdg.sao.icaris.base.MessageStd;
import xyz.satdg.sao.icaris.database.MessageTable;


/**
 * ÏûÏ¢±£´æ
 * @author GongSunink
 */
public class SaveMessages {

    public static MessageTable messageDB= new MessageTable();

    public static void save(long id, String message, String author,String groupName,long groupId){
        messageDB.insert(new MessageStd(id,message,author,groupName,groupId));
    }

}

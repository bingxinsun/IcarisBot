package xyz.satdg.sao.icaris.core.overlord.monitor;

import xyz.satdg.sao.icaris.database.MessageTable;


/**
 * ÏûÏ¢±£´æ
 * @author GongSunink
 */
public class SaveMessages {

    public static MessageTable messageDB= new MessageTable();

    public static void save(long id, String messasge, String author,String groupName,long groupId){
        messageDB.insert(id,messasge,author,groupName,groupId);
    }

}

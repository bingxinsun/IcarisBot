package xyz.satdg.sao.icaris.database;

import xyz.satdg.sao.icaris.api.bases.TableBase;
import xyz.satdg.sao.icaris.core.Mloger.MLoger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

/**
 * ���ݿ⹤����
 * �ṩ����
 * 1.ע�����ݱ�
 * 2.�ṩȫ�����ݿ����Ӷ���
 * 3.�����Ƿ����
 * 4.�ֶ��رշ���
 * @author GongSunink
 */
public class TableHelper {

    private static MLoger loger = new MLoger();

    private static HashMap<String, TableBase> TableMap = new HashMap<>();

    private static Connection gobalConnection;

    /**
     * ���ݿ�ϵͳ��ʼ��
     */
    public void dbSystemInit(){
        for (Map.Entry<String, TableBase> stringDbBaseEntry : TableMap.entrySet()) {
            stringDbBaseEntry.getValue().initTable();
        }
    }

    public TableHelper(String ...dbFiles){
        checkDbs(dbFiles);
    }

    /**
     * ������ݿ��Ƿ����,��ʱû�ж�����ݿ�
     * @param dbFiles ���ݿ���
     */
    private void checkDbs(String ...dbFiles){
        for (String dbfile : dbFiles){
            gobalConnection=creatorConnectDB(dbfile);
        }
    }

    /**
     * ���ȫ�����ݿ����Ӷ���,�˶����ǹ��õ�
     * @return ȫ�����ݿ����Ӷ���
     */
    public static Connection getGobalConnection(){
        return gobalConnection;
    }


    /**
     * ���Ŀ�����ݱ��Ƿ���ڣ������򷵻�true�������ڷ���false
     * @param Table ���ݱ�����
     * @return �Ƿ����
     */
    public static boolean isTableExsit(String Table){
        try{
            gobalConnection.createStatement().execute("select * from " +Table);
        }catch (Exception e){
            loger.error("���ݱ�<"+Table+">������",e);
            return false;
        }
        return true;
    }

    /**
     * ������ݿ�������������ݿ�,�������򴴽�Ȼ��������
     * @param dbName ���ݿ���
     * @return ���ݿ�����
     */
    private static Connection creatorConnectDB(String dbName){
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:"+dbName+".db");
            loger.info("���ݿ���>>><"+dbName+">���سɹ�");
            return c;
        } catch ( Exception e ) {
            loger.error("���ݿ���>>><"+dbName+">����ʧ��",e);
        }

        return c;
    }

    /**
     * ע���
     * @param tableBases
     */
    public void RegistTables(TableBase... tableBases){
        for (TableBase tableBase : tableBases) {
            TableMap.put(tableBase.tableStd().getTableName(), tableBase);
        }
    }

    /**
     * �ֶ��ر�ϵͳ
     */
    public void dumpDbSystem(){
        try{
            gobalConnection.close();
        }catch (SQLException e){
            loger.error(e);
        }

    }
}
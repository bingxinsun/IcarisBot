package xyz.satdg.sao.icaris.core;

import xyz.satdg.sao.icaris.api.bases.TableBase;
import xyz.satdg.sao.icaris.api.marks.Table;
import xyz.satdg.sao.icaris.core.Mloger.MLoger;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author GongSunink
 */
public class DbSystem {
    private static MLoger loger = new MLoger();

    private static HashMap<String, Class<?>> tableMap = new HashMap<>();

    private static Connection gobalConnection;

    public static void jobStart(){
        System.out.println(141414);
        checkDbs("BotDB");
        System.out.println(141414);
        Set<Class<?>> classSet = null;
        try {
            System.out.println(141414);
            classSet = ClassScanner.getClasses("xyz.satdg.sao.icaris.database");
            System.out.println(141414);
        }catch (Exception e){
            loger.error(e.getMessage());
        }
        if (classSet!=null&&!classSet.isEmpty()){
            for (Class c : classSet){
                try {
                    tableMap.put(c.getName(),c);
                    Method initMethod = c.getMethod("initTable");
                    System.out.println(141414);
                    initMethod.invoke(c.newInstance(), (Object[]) null);
                }catch (Exception e){
                    loger.error(e.getCause());
                }
            }
        }
    }

    /**
     * ������ݿ��Ƿ����,��ʱû�ж�����ݿ�
     * @param dbFiles ���ݿ���
     */
    private static void checkDbs(String ...dbFiles){
        for (String dbfile : dbFiles){
            gobalConnection=creatorConnectDB(dbfile);
        }
    }

    /**
     * ���ȫ�����ݿ����Ӷ���,�˶����ǹ��õ�,ʹ��synchronized����ٽ���
     * @return ȫ�����ݿ����Ӷ���
     */
    public static synchronized Connection getGobalConnection(){
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


    private static Connection creatorConnectDB(String dbName){
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:"+dbName+".db");
            loger.info("���ݿ���<"+dbName+">���سɹ�");
            return c;
        } catch ( Exception e ) {
            loger.error("���ݿ���<"+dbName+">����ʧ��",e);
        }
        return c;
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

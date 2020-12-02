package xyz.satdg.sao.icaris.core;

import xyz.satdg.sao.icaris.api.bases.TableBase;
import xyz.satdg.sao.icaris.core.Mloger.MLoger;
import xyz.satdg.sao.icaris.database.MessageTable;
import xyz.satdg.sao.icaris.database.PlayerTable;
import xyz.satdg.sao.icaris.database.SPreplyTable;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

/**
 * ���ݿ�ϵͳ
 * @author GongSunink
 */
public class DbSystem {
    private static HashMap<String, TableBase> tableMap = new HashMap<>();

    private static Connection gobalConnection;

    @SuppressWarnings("unchecked")
    public static void jobStart(){
        MLoger.getLoger().info("�������ݱ�����Զ�����");
        checkDbs("BotDB");
        Set<Class<?>> classSet = null;
        try {
            classSet = ClassScanner.scanPackage("xyz.satdg.sao.icaris.database");
        }catch (Exception e){
            MLoger.getLoger().error(e.getMessage());
        }
        if (classSet!=null&&!classSet.isEmpty()){
            for (Class c : classSet){
                try {
                    if (c.newInstance() instanceof  TableBase){
                        tableMap.put((((TableBase) c.newInstance()).tableStd().getTableName())
                                ,(TableBase)(c.newInstance()));
                        Method initMethod = c.getMethod("initTable");
                        initMethod.invoke(c.newInstance(), (Object[]) null);
                    }
                }catch (Exception e){
                    MLoger.getLoger().error("���ݱ��Զ�����ʧ��,���ڽ����ֶ�����",e.getCause());
                    initByManual(new MessageTable(),new PlayerTable(),new SPreplyTable());
                }
            }
        }else {
            MLoger.getLoger().error("���ݱ��Զ�����ʧ��,���ڽ����ֶ�����");
            initByManual(new MessageTable(),new PlayerTable(),new SPreplyTable());
        }
        MLoger.getLoger().info("���ݱ��Զ��������!");
    }

    private static void initByManual(TableBase ...tableBases){
        for (int i=0;i<tableBases.length;i++){
            tableBases[i].initTable();
        }
    }


    /**
     * ������ݿ��Ƿ����,��ʱû�ж�����ݿ�
     * @param dbFileNames ���ݿ���
     */
    private static void checkDbs(String ...dbFileNames){
        for (String dbFileName : dbFileNames){
            gobalConnection= creatOrConnectDB(dbFileName);
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
    public static boolean isTableExist(String Table){
        try{
            gobalConnection.createStatement().execute("select * from " +Table);
        }catch (Exception e){
            MLoger.getLoger().error("���ݱ�<"+Table+">������",e);
            return false;
        }
        return true;
    }


    private static Connection creatOrConnectDB(String dbName){
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:"+dbName+".db");
            MLoger.getLoger().info("���ݿ���<"+dbName+">���سɹ�");
            return c;
        } catch ( Exception e ) {
            MLoger.getLoger().error("���ݿ���<"+dbName+">����ʧ��",e);
        }
        return c;
    }

    /**
     * �ֶ��ر�ϵͳ
     */
    public static void dumpDbSystem(){
        try{
            gobalConnection.close();
        }catch (SQLException e){
            MLoger.getLoger().error(e);
        }
    }

    public static TableBase getTable(String name){
        return tableMap.getOrDefault(name,null);
    }

}

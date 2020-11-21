package xyz.satdg.sao.icaris.database;

import xyz.satdg.sao.icaris.api.bases.TableBase;
import xyz.satdg.sao.icaris.core.Mloger.MLoger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

/**
 * 数据库工具类
 * 提供功能
 * 1.注册数据表
 * 2.提供全局数据库连接对象
 * 3.检查表是否存在
 * 4.手动关闭方法
 * @author GongSunink
 */
public class TableHelper {

    private static MLoger loger = new MLoger();

    private static HashMap<String, TableBase> TableMap = new HashMap<>();

    private static Connection gobalConnection;

    /**
     * 数据库系统初始化
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
     * 检查数据库是否存在,暂时没有多个数据库
     * @param dbFiles 数据库名
     */
    private void checkDbs(String ...dbFiles){
        for (String dbfile : dbFiles){
            gobalConnection=creatorConnectDB(dbfile);
        }
    }

    /**
     * 获得全局数据库连接对象,此对象是公用的
     * @return 全局数据库连接对象
     */
    public static Connection getGobalConnection(){
        return gobalConnection;
    }


    /**
     * 检查目标数据表是否存在，存在则返回true，不存在返回false
     * @param Table 数据表名称
     * @return 是否存在
     */
    public static boolean isTableExsit(String Table){
        try{
            gobalConnection.createStatement().execute("select * from " +Table);
        }catch (Exception e){
            loger.error("数据表<"+Table+">不存在",e);
            return false;
        }
        return true;
    }

    /**
     * 如果数据库存在则连接数据库,不存在则创建然后再连接
     * @param dbName 数据库名
     * @return 数据库连接
     */
    private static Connection creatorConnectDB(String dbName){
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:"+dbName+".db");
            loger.info("数据库检查>>><"+dbName+">加载成功");
            return c;
        } catch ( Exception e ) {
            loger.error("数据库检查>>><"+dbName+">加载失败",e);
        }

        return c;
    }

    /**
     * 注册表
     * @param tableBases
     */
    public void RegistTables(TableBase... tableBases){
        for (TableBase tableBase : tableBases) {
            TableMap.put(tableBase.tableStd().getTableName(), tableBase);
        }
    }

    /**
     * 手动关闭系统
     */
    public void dumpDbSystem(){
        try{
            gobalConnection.close();
        }catch (SQLException e){
            loger.error(e);
        }

    }
}
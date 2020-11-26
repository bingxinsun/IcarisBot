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
     * 检查数据库是否存在,暂时没有多个数据库
     * @param dbFiles 数据库名
     */
    private static void checkDbs(String ...dbFiles){
        for (String dbfile : dbFiles){
            gobalConnection=creatorConnectDB(dbfile);
        }
    }

    /**
     * 获得全局数据库连接对象,此对象是公用的,使用synchronized封闭临界区
     * @return 全局数据库连接对象
     */
    public static synchronized Connection getGobalConnection(){
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


    private static Connection creatorConnectDB(String dbName){
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:"+dbName+".db");
            loger.info("数据库检查<"+dbName+">加载成功");
            return c;
        } catch ( Exception e ) {
            loger.error("数据库检查<"+dbName+">加载失败",e);
        }
        return c;
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

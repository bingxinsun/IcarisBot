package xyz.satdg.sao.icaris.core;

import xyz.satdg.sao.icaris.api.bases.TableBase;
import xyz.satdg.sao.icaris.core.Loger.IcarisLoger;
import xyz.satdg.sao.icaris.database.MessageTable;
import xyz.satdg.sao.icaris.database.PlayerTable;
import xyz.satdg.sao.icaris.database.SPreplyTable;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

/**
 * 数据库系统
 * @author GongSunink
 */
public class DbSystem {
    private static HashMap<String, TableBase> tableMap = new HashMap<>();

    private static Connection globalConnection;

    @SuppressWarnings("unchecked")
    public static void jobStart(){
        IcarisLoger.getLoger().info("正在数据表进行自动挂载");
        checkDbs("BotDB");
        Set<Class<?>> classSet = null;
        try {
            classSet = ClassScanner.scanSinglePackage("xyz.satdg.sao.icaris.database");
        }catch (ClassNotFoundException | IOException e){
            IcarisLoger.getLoger().error("Package scanner error"+e);
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
                }catch (NoSuchMethodException |InstantiationException |IllegalAccessException |
                InvocationTargetException e){
                    IcarisLoger.getLoger().error("数据表自动挂载失败,正在进行手动挂载",e);
                    initByManual(new MessageTable(),new PlayerTable(),new SPreplyTable());
                }
            }
        }else {
            IcarisLoger.getLoger().error("数据表自动挂载失败,正在进行手动挂载");
            initByManual(new MessageTable(),new PlayerTable(),new SPreplyTable());
        }
        IcarisLoger.getLoger().info("数据表自动挂载完成!");
    }

    private static void initByManual(TableBase ...tableBases){
        for (int i=0;i<tableBases.length;i++){
            tableBases[i].initTable();
        }
    }


    /**
     * 检查数据库是否存在,暂时没有多个数据库
     * @param dbFileNames 数据库名
     */
    private static void checkDbs(String ...dbFileNames){
        for (String dbFileName : dbFileNames){
            globalConnection = creatOrConnectDB(dbFileName);
        }
    }

    /**
     * 获得全局数据库连接对象,此对象是公用的,使用synchronized封闭临界区
     * @return 全局数据库连接对象
     */
    public static synchronized Connection getGlobalConnection(){
        return globalConnection;
    }

    /**
     * 检查目标数据表是否存在，存在则返回true，不存在返回false
     * @param Table 数据表名称
     * @return 是否存在
     */
    public static boolean isTableExist(String Table){
        try{
            globalConnection.createStatement().execute("select * from " +Table);
        }catch (Exception e){
            IcarisLoger.getLoger().error("数据表<"+Table+">不存在",e);
            return false;
        }
        return true;
    }


    private static Connection creatOrConnectDB(String dbName){
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:"+dbName+".db");
            IcarisLoger.getLoger().info("数据库检查<"+dbName+">加载成功");
            return c;
        } catch ( Exception e ) {
            IcarisLoger.getLoger().error("数据库检查<"+dbName+">加载失败",e);
        }
        return c;
    }

    /**
     * 手动关闭系统
     */
    public static void dumpDbSystem(){
        try{
            globalConnection.close();
        }catch (SQLException e){
            IcarisLoger.getLoger().error(e);
        }
    }

    public static TableBase getTable(String name){
        return tableMap.getOrDefault(name,null);
    }

}

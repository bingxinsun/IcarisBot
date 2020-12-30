package xyz.satdg.sao.icaris.core;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import xyz.satdg.sao.icaris.api.bases.TableBase;
import xyz.satdg.sao.icaris.database.MessageTable;
import xyz.satdg.sao.icaris.database.PlayerTable;
import xyz.satdg.sao.icaris.database.sPreplytable;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

import static xyz.satdg.sao.icaris.core.IcarisBotSystem.ICARIS_LOGGER;
/**
 * 数据库系统
 * @author GongSunink
 */
public final class DbSystem extends Loader {
    private static HashMap<String, TableBase> tableMap = new HashMap<>();

    private static Connection globalConnection;

    @SuppressWarnings("unchecked")
    public static void jobStart(){
        ICARIS_LOGGER.info("正在数据表进行自动挂载");
        checkDbs("BotDB");
        Set<Class<?>> classSet = null;
        try {
            classSet = ClassScanner.scanSinglePackage("xyz.satdg.sao.icaris.database");
        }catch (ClassNotFoundException | IOException e){
            ICARIS_LOGGER.error("Package scanner error"+e);
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
                    ICARIS_LOGGER.error("数据表自动挂载失败,正在进行手动挂载",e);
                    initByManual(new MessageTable(), new PlayerTable(), new sPreplytable());
                }
            }
        }else {
            ICARIS_LOGGER.error("数据表自动挂载失败,正在进行手动挂载");
            initByManual(new MessageTable(), new PlayerTable(), new sPreplytable());
        }
        ICARIS_LOGGER.info("数据表自动挂载完成!");
    }

    private static void initByManual(TableBase ...tableBases){
        for (TableBase tableBase : tableBases) {
            tableBase.initTable();
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
     * @param table 数据表名称
     * @return 是否存在
     */
    public static boolean isTableExist(String table){
        try{
            globalConnection.createStatement().execute("select * from " +table);
        }catch (Exception e){
            ICARIS_LOGGER.error("数据表<"+table+">不存在",e);
            return false;
        }
        return true;
    }


    private static Connection creatOrConnectDB(String dbName){
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:"+dbName+".db");
            ICARIS_LOGGER.info("数据库检查<"+dbName+">加载成功");
            return c;
        } catch ( Exception e ) {
            ICARIS_LOGGER.error("数据库检查<"+dbName+">加载失败",e);
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
            ICARIS_LOGGER.error(e);
        }
    }

    public static TableBase getTable(String name){
        return tableMap.getOrDefault(name,null);
    }
}
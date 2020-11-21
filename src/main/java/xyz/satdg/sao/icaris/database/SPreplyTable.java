package xyz.satdg.sao.icaris.database;

import xyz.satdg.sao.icaris.api.bases.TableBase;
import xyz.satdg.sao.icaris.base.TableStd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 特殊回复表
 * @author GongSunink
 */
public class SPreplyTable extends TableBase {

    private Connection connection;

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public TableStd tableStd() {
        return new TableStd("SPLICALREPLYTABLE");
    }


    public SPreplyTable() {
       this.connection = TableHelper.getGobalConnection();
    }



    public boolean delete(String message){
        try{
            connection.createStatement().executeUpdate("DELETE from SPLICALREPLYTABLE where MESSAGE ="+message);
            return true;
        }catch (SQLException e){
            getLogger().error(e);
            return false;
        }

    }


    /**
     * 选择流程
     * 1.从表中获得一个由表中元素构成的Resultset
     * 2.通过.next方法迭代遍历set,如果从MESSAGE行和请求的消息符合,则返回RETURN行的内容
     * 单查
     * @param message
     * @return
     */
    public String select(String message){
        try{
            ResultSet set= connection.createStatement().executeQuery("SELECT * FROM SPLICALREPLYTABLE");
            String result = null;
            while(set.next()){
                if (message.equals(set.getString("MESSAGE"))){
                    result = set.getString("RETURN");
                    set.close();
                    return result;
                }else {
                    result = null;
                }
            }
            return result;
        }catch (SQLException e){
            this.getLogger().error(e);
        }
        return null;
    }


    public void insert(long id,String message,String messageReturn,String groupName,long groupId,String author) {
        try {
            connection.createStatement().executeUpdate("INSERT INTO SPLICALREPLYTABLE(ID, MESSAGE,RETURN,GROUPNAME,GROUPID, AUTHOR)" +
                    "VALUES("+id+",'"+message+"','"+messageReturn+"','"+groupName+"','"+groupId+"','"+author+"');");
            /*
             * 注意statement执行结束后一定要关闭，否则可能会造成SQL_BUSY Exception,这里直接使用临时对象，结束后
             * 对象直接被销毁
             */
        }catch (SQLException e){
            this.getLogger().error("消息记录保存失败<" + this.tableStd().getTableName()+ ">",e);
        }
    }

    @Override
    public void initTable() {
        if (TableHelper.isTableExsit(this.tableStd().getTableName())) {
            this.getLogger().info("数据表<" + this.tableStd().getTableName() + ">加载完成");
        }else {
            try {
                this.getLogger().info("数据表<" + this.tableStd().getTableName()+ ">不存在，正在创建表");
                this.connection.createStatement().execute("CREATE TABLE SPLICALREPLYTABLE(" +
                        "ID INT  NOT NULL," +
                        "MESSAGE  TEXT  NOT NULL," +
                        "RETURN  TEXT  NOT NULL," +
                        "GROUPNAME TEXT," +
                        "GROUPID INTEGER," +
                        "AUTHOR TEXT NOT NULL)");
                this.getLogger().info("数据表<" + this.tableStd().getTableName() + ">创建成功");
            } catch (SQLException e) {
                this.getLogger().error("数据表<" + this.tableStd().getTableName() + ">创建失败", e);
            }
        }
    }
}
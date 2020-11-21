package xyz.satdg.sao.icaris.database;

import xyz.satdg.sao.icaris.api.bases.TableBase;
import xyz.satdg.sao.icaris.api.marks.TableActions;
import xyz.satdg.sao.icaris.base.TableStd;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 消息存储表
 * @author GongSunink
 */
public class MessageTable extends TableBase {

    private Connection connection;

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public TableStd tableStd() {
        return new TableStd("MESSAGETABLE");
    }

    public MessageTable() {
        this.connection = TableHelper.getGobalConnection();
    }

    /**
     * 插入函数
     * @param id 发送者id
     * @param message 信息
     * @param author 发送者名字
     * @param groupName 群名字
     * @param groupId 群id
     */
    @TableActions(TableName = "MESSAGETABLE",value = TableActions.actionType.INSERT)
    public void insert(long id,String message,String author,String groupName,long groupId){
        try {
            connection.createStatement().executeUpdate("INSERT INTO MESSAGETABLE(ID,MESSAGE,AUTHOR,GROUPNAME,GROUPID)"+
                    "VALUES("+id+",'"+message+"','"+author+"','"+groupName+"','"+groupId+"');");
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
            this.getLogger().info("数据表<" +this.tableStd().getTableName() + ">加载完成");
        }else {
            try {
                this.getLogger().info("数据表<" + this.tableStd().getTableName()+ ">不存在，正在创建表");
                connection.createStatement().execute("CREATE TABLE MESSAGETABLE"
                        + "(ID INTEGER NOT NULL," +
                        "MESSAGE  TEXT  NOT NULL," +
                        "AUTHOR TEXT NOT NULL," +
                        "GROUPNAME TEXT," +
                        "GROUPID INTEGER)");
                this.getLogger().info("数据表<" + this.tableStd().getTableName() + ">创建成功");
            } catch (SQLException e) {
                this.getLogger().error("数据表<" +this.tableStd().getTableName() + ">创建失败", e);
            }
        }
    }
}

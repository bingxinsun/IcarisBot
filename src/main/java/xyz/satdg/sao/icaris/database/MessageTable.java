package xyz.satdg.sao.icaris.database;

import xyz.satdg.sao.icaris.api.bases.DbObject;
import xyz.satdg.sao.icaris.api.bases.TableBase;
import xyz.satdg.sao.icaris.api.marks.Table;
import xyz.satdg.sao.icaris.api.marks.TableActions;
import xyz.satdg.sao.icaris.base.MessageStd;
import xyz.satdg.sao.icaris.base.TableStd;
import xyz.satdg.sao.icaris.core.DbSystem;
import xyz.satdg.sao.icaris.core.Mloger.MLoger;

import java.sql.SQLException;

/**
 * 消息存储表
 * @author GongSunink
 */
@Table(tableName = "MESSAGETABLE",dbName = "BotDB")
public class MessageTable extends TableBase {


    @Override
    public TableStd tableStd() {
        return new TableStd("MESSAGETABLE");
    }

    @Override
    public DbObject select(DbObject object) {
        return null;
    }

    /**
     * 插入函数
     */
    @TableActions(TableName = "MESSAGETABLE", action = TableActions.actionType.INSERT)
    @Override
    public void insert(DbObject dbObject){
        if (dbObject instanceof MessageStd){
            try {
                DbSystem.getGobalConnection().createStatement().executeUpdate(String.format("" +
                                "INSERT INTO MESSAGETABLE(ID,MESSAGE,AUTHOR,GROUPNAME,GROUPID)VALUES(" +
                                "%d,'%s','%s','%s','%d');",
                                ((MessageStd)dbObject).getSenderId(),
                                ((MessageStd)dbObject).getMessage(),
                                ((MessageStd)dbObject).getSenderNick(),
                                ((MessageStd)dbObject).getGroupName(),
                                ((MessageStd)dbObject).getGrouopId()));
                /*
                 * 注意statement执行结束后一定要关闭，否则可能会造成SQL_BUSY Exception,这里直接使用临时对象，结束后
                 * 对象直接被销毁
                 */
            }catch (SQLException e){
                MLoger.getLoger().error("消息记录保存失败<" + this.tableStd().getTableName()+ ">",e);
            }
        }

    }

    @Override
    public void initTable() {
        if (DbSystem.isTableExist(this.tableStd().getTableName())) {
            MLoger.getLoger().info("数据表<" +this.tableStd().getTableName() + ">加载完成");
        }else {
            try {
                MLoger.getLoger().info("数据表<" + this.tableStd().getTableName()+ ">不存在，正在创建表");
                DbSystem.getGobalConnection().createStatement().execute("CREATE TABLE MESSAGETABLE"
                        + "(ID INTEGER NOT NULL," +
                        "MESSAGE  TEXT  NOT NULL," +
                        "AUTHOR TEXT NOT NULL," +
                        "GROUPNAME TEXT," +
                        "GROUPID INTEGER)");
                MLoger.getLoger().info("数据表<" + this.tableStd().getTableName() + ">创建成功");
            } catch (SQLException e) {
                MLoger.getLoger().error("数据表<" +this.tableStd().getTableName() + ">创建失败", e);
            }
        }
    }
}

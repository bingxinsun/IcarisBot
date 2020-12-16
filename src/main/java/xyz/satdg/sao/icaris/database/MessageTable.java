package xyz.satdg.sao.icaris.database;

import xyz.satdg.sao.icaris.api.bases.TableBase;
import xyz.satdg.sao.icaris.api.marks.Table;
import xyz.satdg.sao.icaris.base.MessageStd;
import xyz.satdg.sao.icaris.base.TableStd;
import xyz.satdg.sao.icaris.core.DbSystem;
import xyz.satdg.sao.icaris.core.loger.exception.MessageSaveFailedException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static xyz.satdg.sao.icaris.core.IcarisBotSystem.ICARIS_LOGGER;


/**
 * 消息存储表
 * @author GongSunink
 */
@Table(tableName = "MessageTable",dbName = "BotDB")
public class MessageTable extends TableBase<MessageStd> {

    @Override
    public MessageStd select(MessageStd object) {
        return null;
    }

    @Override
    public boolean update(MessageStd object) {
        return false;
    }

    @Override
    public TableStd tableStd() {
        return new TableStd("MessageTable");
    }

    /**
     * 插入函数
     */
    @Override
    public void insert(MessageStd messageStd){
            try {
                PreparedStatement statement = DbSystem.getGlobalConnection().prepareStatement("" +
                        "insert into MESSAGETABLE values(?,?,?,?,?)");
                statement.setLong(1,messageStd.getSenderId());
                statement.setString(2,messageStd.getMessage());
                statement.setString(3,messageStd.getSenderNick());
                statement.setString(4,messageStd.getGroupName());
                statement.setLong(5,messageStd.getGrouopId());

                statement.execute();
                statement.close();
                /*
                 * 注意statement执行结束后一定要关闭，否则可能会造成SQL_BUSY Exception,这里直接使用临时对象，结束后
                 * 对象直接被销毁
                 */
            }catch (SQLException e){
                ICARIS_LOGGER.error(new MessageSaveFailedException(this));
            }
        }

    @Override
    public void initTable() {
        if (DbSystem.isTableExist(this.tableStd().getTableName())) {
            ICARIS_LOGGER.info("数据表<" +this.tableStd().getTableName() + ">加载完成");
        }else {
            try {
                ICARIS_LOGGER.info("数据表<" + this.tableStd().getTableName()+
                        ">不存在，正在创建表");
                DbSystem.getGlobalConnection().createStatement().execute("CREATE " +
                        "TABLE MessageTable"
                        + "(ID INTEGER NOT NULL," +
                        "Message  TEXT  NOT NULL," +
                        "Author TEXT NOT NULL," +
                        "GroupName TEXT," +
                        "GroupId INTEGER)");
                ICARIS_LOGGER.info("数据表<" + this.tableStd().getTableName() +
                        ">创建成功");
            } catch (SQLException e) {
                ICARIS_LOGGER.error("数据表<" +this.tableStd().getTableName() +
                        ">创建失败", e);
            }
        }
    }
}

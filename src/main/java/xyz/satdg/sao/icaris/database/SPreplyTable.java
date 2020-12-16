package xyz.satdg.sao.icaris.database;

import xyz.satdg.sao.icaris.api.bases.TableBase;
import xyz.satdg.sao.icaris.api.marks.Table;
import xyz.satdg.sao.icaris.base.SpMessageStd;
import xyz.satdg.sao.icaris.base.TableStd;
import xyz.satdg.sao.icaris.core.DbSystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static xyz.satdg.sao.icaris.core.IcarisBotSystem.ICARIS_LOGGER;

/**
 * special reply datatable
 * @author GongSunink
 */
@Table(tableName = "SpReplyTable",dbName = "BotDB")
public class SPreplyTable extends TableBase<SpMessageStd> {

    @Override
    public TableStd tableStd() {
        return new TableStd("SpReplyTable");
    }

    @Override
    public SpMessageStd select(SpMessageStd object) {
        return null;
    }

    @Override
    public boolean update(SpMessageStd object) {
        return false;
    }

    public boolean delete(String message){
        try{
            DbSystem.getGlobalConnection().createStatement().executeUpdate(
                    "DELETE from SpReplyTable where Message ="+message);
            return true;
        }catch (SQLException e){
            ICARIS_LOGGER.error("SpMessage delete failed in table ",e);
            return false;
        }
    }

    /**
     * choose sequence:
     * 1.convent TableData into an ResultSet
     * 2.using next() to iterate the set
     * if set dose not contain the require message,return null as "not found"
     * @param message message which require
     * @return message found
     */
    public String select(String message){
        try{
            ResultSet set= DbSystem.getGlobalConnection().createStatement().executeQuery(
                    "SELECT " +
                    "* FROM SpReplyTable");
            String result = null;
            while(set.next()){
                if (message.contains(set.getString("Message"))){
                    result = set.getString("ReturnMessage");
                    set.close();
                    return result;
                }
            }
            return result;
        }catch (SQLException e){
            ICARIS_LOGGER.error(e);
        }
        return null;
    }

    @Override
    public void insert(SpMessageStd spMessageStd) {
            try {
                PreparedStatement statement = DbSystem.getGlobalConnection().prepareStatement(
                        "insert into SpReplyTable " + "values(?,?,?,?,?,?)");
                statement.setLong(1,spMessageStd.getSenderId());
                statement.setString(2,spMessageStd.getMessage());
                statement.setString(3,spMessageStd.getReturnMessage());
                statement.setString(4,spMessageStd.getGroupName());
                statement.setLong(5,spMessageStd.getGrouopId());
                statement.setString(6,spMessageStd.getSenderNick());
                /*
                 * consider the statement and connection
                 * ,when finish using them, must release lock
                 */
            }catch (SQLException e){
                ICARIS_LOGGER.error("Message Record Failed<" +
                        this.tableStd().getTableName()+ ">",e);
            }
    }

    @Override
    public void initTable() {
        if (DbSystem.isTableExist(this.tableStd().getTableName())) {
            ICARIS_LOGGER.info("Data Table<" + this.tableStd().getTableName() +
                    ">Load Successful");
        }else {
            try {
                ICARIS_LOGGER.info("Data Table<" + this.tableStd().getTableName()+
                        ">dose not Exist,Creating a new");
                DbSystem.getGlobalConnection().createStatement().execute("CREATE TABLE " +
                        "SpReplyTable(" +
                        "ID INT  NOT NULL," +
                        "Message  TEXT  NOT NULL," +
                        "ReturnMessage  TEXT  NOT NULL," +
                        "FromGroupName TEXT," +
                        "FromGroupId INTEGER," +
                        "Author TEXT NOT NULL)");
                ICARIS_LOGGER.info("Data Table<" + this.tableStd().getTableName() +
                        ">Creat Successful");
            } catch (SQLException e) {
                ICARIS_LOGGER.error("Data Table<" + this.tableStd().getTableName() +
                        ">Creat Successful", e);
            }
        }
    }

}
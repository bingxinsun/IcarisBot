package xyz.satdg.sao.icaris.database;

import xyz.satdg.sao.icaris.api.bases.DbObject;
import xyz.satdg.sao.icaris.api.bases.TableBase;
import xyz.satdg.sao.icaris.api.marks.Table;
import xyz.satdg.sao.icaris.base.SpMessageStd;
import xyz.satdg.sao.icaris.base.TableStd;
import xyz.satdg.sao.icaris.core.DbSystem;
import xyz.satdg.sao.icaris.core.Mloger.MLoger;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * special reply datatable
 * @author GongSunink
 */
@Table(tableName = "SPLICALREPLYTABLE",dbName = "BotDB")
public class SPreplyTable extends TableBase {

    @Override
    public TableStd tableStd() {
        return new TableStd("SPLICALREPLYTABLE");
    }

    @Override
    public DbObject select(DbObject object) {
        return null;
    }

    public boolean delete(String message){
        try{
            DbSystem.getGobalConnection().createStatement().executeUpdate(
                    "DELETE from SPLICALREPLYTABLE where MESSAGE ="+message);
            return true;
        }catch (SQLException e){
            MLoger.getLoger().error(e);
            return false;
        }

    }


    /**
     * choose sequence:
     * 1.choose a ResultSet for table
     * 2.using next() to iterate the set
     * if set dose not contain the require message,return null as "not found"
     * @param message message which require
     * @return message found
     */
    public String select(String message){
        try{
            ResultSet set= DbSystem.getGobalConnection().createStatement().executeQuery("SELECT * FROM SPLICALREPLYTABLE");
            String result = null;
            while(set.next()){
                if (message.contains(set.getString("MESSAGE"))){
                    result = set.getString("RETURN");
                    set.close();
                    return result;
                }else {
                    result = null;
                }
            }

            return result;
        }catch (SQLException e){
            MLoger.getLoger().error(e);
        }
        return null;
    }

    @Override
    public void insert(DbObject dbObject) {
        if (dbObject instanceof SpMessageStd){
            try {
                DbSystem.getGobalConnection().createStatement().executeUpdate(
                        String.format("INSERT INTO SPLICALREPLYTABLE(" +
                                "ID, MESSAGE,RETURN,GROUPNAME,GROUPID, AUTHOR)" +
                                "VALUES(%d,'%s','%s','%s','%d','%s');",
                                ((SpMessageStd)dbObject).getSenderId(),
                                ((SpMessageStd)dbObject).getMessage(),
                                ((SpMessageStd)dbObject).getReturnMessage(),
                                ((SpMessageStd)dbObject).getGroupName(),
                                ((SpMessageStd)dbObject).getGrouopId(),
                                ((SpMessageStd)dbObject).getSenderNick()));
                /*
                 * consider the statement and connection,when finish using them, must release lock
                 */
            }catch (SQLException e){
                MLoger.getLoger().error("Message Record Failed<" + this.tableStd().getTableName()+ ">",e);
            }
        }

    }

    @Override
    public void initTable() {
        if (DbSystem.isTableExist(this.tableStd().getTableName())) {
            MLoger.getLoger().info("Data Table<" + this.tableStd().getTableName() + ">Load Successful");
        }else {
            try {
                MLoger.getLoger().info("Data Table<" + this.tableStd().getTableName()+ ">dose not Exist,Creating a new");
                DbSystem.getGobalConnection().createStatement().execute("CREATE TABLE SPLICALREPLYTABLE(" +
                        "ID INT  NOT NULL," +
                        "MESSAGE  TEXT  NOT NULL," +
                        "RETURN  TEXT  NOT NULL," +
                        "GROUPNAME TEXT," +
                        "GROUPID INTEGER," +
                        "AUTHOR TEXT NOT NULL)");
                MLoger.getLoger().info("Data Table<" + this.tableStd().getTableName() + ">Creat Successful");
            } catch (SQLException e) {
                MLoger.getLoger().error("Data Table<" + this.tableStd().getTableName() + ">Creat Successful", e);
            }
        }
    }
}
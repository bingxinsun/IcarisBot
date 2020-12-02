package xyz.satdg.sao.icaris.database;

import xyz.satdg.sao.icaris.api.bases.DbObject;
import xyz.satdg.sao.icaris.api.bases.TableBase;
import xyz.satdg.sao.icaris.api.marks.Table;
import xyz.satdg.sao.icaris.base.PlayerStd;
import xyz.satdg.sao.icaris.base.TableStd;
import xyz.satdg.sao.icaris.core.DbSystem;
import xyz.satdg.sao.icaris.core.Mloger.MLoger;

import java.sql.SQLException;

/**
 * @author GongSunink
 */
@Table(tableName = "PLAYERTABLE",dbName = "BotDB")
public class PlayerTable extends TableBase {


    @Override
    public TableStd tableStd() {
        return new TableStd("PLAYERTABLE");
    }


    @Override
    public DbObject select(DbObject object) {
        return null;
    }

    public String select(String name){
        return null;
    }



    @Override
    public void insert(DbObject dbObject){
        if (dbObject instanceof PlayerStd){
            try{
                DbSystem.getGobalConnection().createStatement().executeUpdate(String.format("" +
                                "INSERT INTO PLAYERTABLE(ID,LEVEL,NICK,FEELINGS,CALL_NAME)" +
                                " VALUES(%d,%d,'%s',%d,'%s',",
                                ((PlayerStd)dbObject).getQQid(),
                                ((PlayerStd)dbObject).getLevel(),
                                ((PlayerStd)dbObject).getNick(),
                                ((PlayerStd)dbObject).getEXP(),
                                ((PlayerStd)dbObject).getCallName()));
            }catch (SQLException e){
                MLoger.getLoger().error(e);
            }
        }

    }

    @Override
    public void initTable() {
        if (DbSystem.isTableExist(this.tableStd().getTableName())) {
            MLoger.getLoger().info("数据表<" + this.tableStd().getTableName() + ">加载完成");
        } else {
            try {
                MLoger.getLoger().info("数据表<" + this.tableStd().getTableName() + ">不存在，正在创建表");
                DbSystem.getGobalConnection().createStatement().execute("CREATE TABLE PLAYERTABLE"
                        + "(ID INTEGER NOT NULL," +
                        "LEVEL INTEGER DEFAULT 0," +
                        "NICK TEXT NOT NULL," +
                        "EXP INTEGER DEFAULT 0," +
                        "CALL_NAME TEXT)");
                MLoger.getLoger().info("数据表<" + this.tableStd().getTableName() + ">创建成功");
            } catch (SQLException e) {
                MLoger.getLoger().error("数据表<" + this.tableStd().getTableName() + ">创建失败", e);
            }
        }
    }

}

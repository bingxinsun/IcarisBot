package xyz.satdg.sao.icaris.database;

import xyz.satdg.sao.icaris.api.bases.TableBase;
import xyz.satdg.sao.icaris.api.marks.Table;
import xyz.satdg.sao.icaris.base.PlayerStd;
import xyz.satdg.sao.icaris.base.TableStd;
import xyz.satdg.sao.icaris.core.DbSystem;
import xyz.satdg.sao.icaris.core.Mloger.MLoger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author GongSunink
 */
@Table(tableName = "PlayerTable",dbName = "BotDB")
public class PlayerTable extends TableBase<PlayerStd> {

    @Override
    public TableStd tableStd() {
        return new TableStd("PlayerTable");
    }

    @Override
    public void insert(PlayerStd playerStd) {
        try{
            PreparedStatement statement = DbSystem.getGlobalConnection().prepareStatement(
                    "insert into PlayerTable values(?,?,?,?,?)"
            );
            statement.setLong(1,playerStd.getQQId());
            statement.setInt(2,playerStd.getLevel());
            statement.setString(3,playerStd.getNick());
            statement.setInt(4,playerStd.getEXP());
            statement.setString(5,playerStd.getCallName());
        }catch (SQLException e){
            MLoger.getLoger().error(e);
        }
    }

    @Override
    public PlayerStd select(PlayerStd object) {
        return null;
    }

    @Override
    public boolean update(PlayerStd object) {
        return false;
    }


    @Override
    public void initTable() {
        if (DbSystem.isTableExist(this.tableStd().getTableName())) {
            MLoger.getLoger().info("数据表<" + this.tableStd().getTableName() + ">加载完成");
        } else {
            try {
                MLoger.getLoger().info("数据表<" + this.tableStd().getTableName() + ">不存在，正在创建表");
                DbSystem.getGlobalConnection().createStatement().execute("" +
                        "CREATE TABLE PlayerTable"
                        + "(ID INTEGER NOT NULL," +
                        "Level INTEGER DEFAULT 0," +
                        "Nick TEXT NOT NULL," +
                        "EXP INTEGER DEFAULT 0," +
                        "CallName TEXT)");
                MLoger.getLoger().info("数据表<" + this.tableStd().getTableName() + ">创建成功");
            } catch (SQLException e) {
                MLoger.getLoger().error("数据表<" + this.tableStd().getTableName() + ">创建失败", e);
            }
        }
    }

}

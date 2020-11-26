package xyz.satdg.sao.icaris.database;

import xyz.satdg.sao.icaris.api.bases.DbObject;
import xyz.satdg.sao.icaris.api.bases.TableBase;
import xyz.satdg.sao.icaris.api.marks.Table;
import xyz.satdg.sao.icaris.base.PlayerStd;
import xyz.satdg.sao.icaris.base.TableStd;
import xyz.satdg.sao.icaris.core.DbSystem;

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


    public void selete(){

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
                getLogger().error(e);
            }
        }

    }

    @Override
    public void initTable() {
        if (DbSystem.isTableExsit(this.tableStd().getTableName())) {
            this.getLogger().info("���ݱ�<" + this.tableStd().getTableName() + ">�������");
        } else {
            try {
                this.getLogger().info("���ݱ�<" + this.tableStd().getTableName() + ">�����ڣ����ڴ�����");
                DbSystem.getGobalConnection().createStatement().execute("CREATE TABLE PLAYERTABLE"
                        + "(ID INTEGER NOT NULL," +
                        "LEVEL INTEGER DEFAULT 0," +
                        "NICK TEXT NOT NULL," +
                        "EXP INTEGER DEFAULT 0," +
                        "CALL_NAME TEXT)");
                this.getLogger().info("���ݱ�<" + this.tableStd().getTableName() + ">�����ɹ�");
            } catch (SQLException e) {
                this.getLogger().error("���ݱ�<" + this.tableStd().getTableName() + ">����ʧ��", e);
            }
        }
    }

}

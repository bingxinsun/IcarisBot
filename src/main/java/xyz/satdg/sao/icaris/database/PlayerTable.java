package xyz.satdg.sao.icaris.database;

import xyz.satdg.sao.icaris.api.bases.TableBase;
import xyz.satdg.sao.icaris.base.PlayerStd;
import xyz.satdg.sao.icaris.base.TableStd;

import java.sql.SQLException;

/**
 * @author GongSunink
 */
public class PlayerTable extends TableBase {


    @Override
    public TableStd tableStd() {
        return new TableStd("PLAYERTABLE");
    }


    public void selete(){

    }


    public void insert(PlayerStd playerStd){
        try{
            TableHelper.getGobalConnection().createStatement().executeUpdate(String.format("" +
                    "INSERT INTO PLAYERTABLE(ID,LEVEL,NICK,FEELINGS,CALL_NAME)" +
                    " VALUES(%d,%d,'%s',%d,'%s',",
                    playerStd.getQQid(), playerStd.getLevel(),
                    playerStd.getNick(),
                    playerStd.getEXP(), playerStd.getCallName()));
        }catch (SQLException e){
            getLogger().error(e);
       }
    }

    @Override
    public void initTable() {
        if (TableHelper.isTableExsit(this.tableStd().getTableName())) {
            this.getLogger().info("���ݱ�<" + this.tableStd().getTableName() + ">�������");
        } else {
            try {
                this.getLogger().info("���ݱ�<" + this.tableStd().getTableName() + ">�����ڣ����ڴ�����");
                TableHelper.getGobalConnection().createStatement().execute("CREATE TABLE PLAYERTABLE"
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

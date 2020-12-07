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
 * ��Ϣ�洢��
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
     * ���뺯��
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
                 * ע��statementִ�н�����һ��Ҫ�رգ�������ܻ����SQL_BUSY Exception,����ֱ��ʹ����ʱ���󣬽�����
                 * ����ֱ�ӱ�����
                 */
            }catch (SQLException e){
                MLoger.getLoger().error("��Ϣ��¼����ʧ��<" + this.tableStd().getTableName()+ ">",e);
            }
        }

    }

    @Override
    public void initTable() {
        if (DbSystem.isTableExist(this.tableStd().getTableName())) {
            MLoger.getLoger().info("���ݱ�<" +this.tableStd().getTableName() + ">�������");
        }else {
            try {
                MLoger.getLoger().info("���ݱ�<" + this.tableStd().getTableName()+ ">�����ڣ����ڴ�����");
                DbSystem.getGobalConnection().createStatement().execute("CREATE TABLE MESSAGETABLE"
                        + "(ID INTEGER NOT NULL," +
                        "MESSAGE  TEXT  NOT NULL," +
                        "AUTHOR TEXT NOT NULL," +
                        "GROUPNAME TEXT," +
                        "GROUPID INTEGER)");
                MLoger.getLoger().info("���ݱ�<" + this.tableStd().getTableName() + ">�����ɹ�");
            } catch (SQLException e) {
                MLoger.getLoger().error("���ݱ�<" +this.tableStd().getTableName() + ">����ʧ��", e);
            }
        }
    }
}

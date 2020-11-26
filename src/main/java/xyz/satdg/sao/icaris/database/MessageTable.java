package xyz.satdg.sao.icaris.database;

import xyz.satdg.sao.icaris.api.bases.TableBase;
import xyz.satdg.sao.icaris.api.marks.TableActions;
import xyz.satdg.sao.icaris.base.MessageStd;
import xyz.satdg.sao.icaris.base.TableStd;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * ��Ϣ�洢��
 * @author GongSunink
 */
public class MessageTable extends TableBase {


    @Override
    public TableStd tableStd() {
        return new TableStd("MESSAGETABLE");
    }

    /**
     * ���뺯��
     * @param messagestd message����
     */
    @TableActions(TableName = "MESSAGETABLE",value = TableActions.actionType.INSERT)
    public void insert(MessageStd messagestd){
        try {
            TableHelper.getGobalConnection().createStatement().executeUpdate("INSERT INTO " +
                    "MESSAGETABLE(ID,MESSAGE,AUTHOR,GROUPNAME,GROUPID)"+
                    "VALUES("+messagestd.getSenderId()+",'"+messagestd.getMessage()+"" +
                    "','"+messagestd.getSenderNick()+"','"+messagestd.getGroupName()+"'" +
                    ",'"+messagestd.getGrouopId()+"');");
            /*
             * ע��statementִ�н�����һ��Ҫ�رգ�������ܻ����SQL_BUSY Exception,����ֱ��ʹ����ʱ���󣬽�����
             * ����ֱ�ӱ�����
             */
        }catch (SQLException e){
            this.getLogger().error("��Ϣ��¼����ʧ��<" + this.tableStd().getTableName()+ ">",e);
        }
    }

    @Override
    public void initTable() {
        if (TableHelper.isTableExsit(this.tableStd().getTableName())) {
            this.getLogger().info("���ݱ�<" +this.tableStd().getTableName() + ">�������");
        }else {
            try {
                this.getLogger().info("���ݱ�<" + this.tableStd().getTableName()+ ">�����ڣ����ڴ�����");
                TableHelper.getGobalConnection().createStatement().execute("CREATE TABLE MESSAGETABLE"
                        + "(ID INTEGER NOT NULL," +
                        "MESSAGE  TEXT  NOT NULL," +
                        "AUTHOR TEXT NOT NULL," +
                        "GROUPNAME TEXT," +
                        "GROUPID INTEGER)");
                this.getLogger().info("���ݱ�<" + this.tableStd().getTableName() + ">�����ɹ�");
            } catch (SQLException e) {
                this.getLogger().error("���ݱ�<" +this.tableStd().getTableName() + ">����ʧ��", e);
            }
        }
    }
}

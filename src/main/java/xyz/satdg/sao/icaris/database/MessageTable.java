package xyz.satdg.sao.icaris.database;

import xyz.satdg.sao.icaris.api.bases.TableBase;
import xyz.satdg.sao.icaris.api.marks.TableActions;
import xyz.satdg.sao.icaris.base.TableStd;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * ��Ϣ�洢��
 * @author GongSunink
 */
public class MessageTable extends TableBase {

    private Connection connection;

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public TableStd tableStd() {
        return new TableStd("MESSAGETABLE");
    }

    public MessageTable() {
        this.connection = TableHelper.getGobalConnection();
    }

    /**
     * ���뺯��
     * @param id ������id
     * @param message ��Ϣ
     * @param author ����������
     * @param groupName Ⱥ����
     * @param groupId Ⱥid
     */
    @TableActions(TableName = "MESSAGETABLE",value = TableActions.actionType.INSERT)
    public void insert(long id,String message,String author,String groupName,long groupId){
        try {
            connection.createStatement().executeUpdate("INSERT INTO MESSAGETABLE(ID,MESSAGE,AUTHOR,GROUPNAME,GROUPID)"+
                    "VALUES("+id+",'"+message+"','"+author+"','"+groupName+"','"+groupId+"');");
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
                connection.createStatement().execute("CREATE TABLE MESSAGETABLE"
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

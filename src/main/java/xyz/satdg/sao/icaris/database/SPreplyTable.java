package xyz.satdg.sao.icaris.database;

import xyz.satdg.sao.icaris.api.bases.TableBase;
import xyz.satdg.sao.icaris.base.TableStd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ����ظ���
 * @author GongSunink
 */
public class SPreplyTable extends TableBase {

    private Connection connection;

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public TableStd tableStd() {
        return new TableStd("SPLICALREPLYTABLE");
    }


    public SPreplyTable() {
       this.connection = TableHelper.getGobalConnection();
    }



    public boolean delete(String message){
        try{
            connection.createStatement().executeUpdate("DELETE from SPLICALREPLYTABLE where MESSAGE ="+message);
            return true;
        }catch (SQLException e){
            getLogger().error(e);
            return false;
        }

    }


    /**
     * ѡ������
     * 1.�ӱ��л��һ���ɱ���Ԫ�ع��ɵ�Resultset
     * 2.ͨ��.next������������set,�����MESSAGE�к��������Ϣ����,�򷵻�RETURN�е�����
     * ����
     * @param message
     * @return
     */
    public String select(String message){
        try{
            ResultSet set= connection.createStatement().executeQuery("SELECT * FROM SPLICALREPLYTABLE");
            String result = null;
            while(set.next()){
                if (message.equals(set.getString("MESSAGE"))){
                    result = set.getString("RETURN");
                    set.close();
                    return result;
                }else {
                    result = null;
                }
            }
            return result;
        }catch (SQLException e){
            this.getLogger().error(e);
        }
        return null;
    }


    public void insert(long id,String message,String messageReturn,String groupName,long groupId,String author) {
        try {
            connection.createStatement().executeUpdate("INSERT INTO SPLICALREPLYTABLE(ID, MESSAGE,RETURN,GROUPNAME,GROUPID, AUTHOR)" +
                    "VALUES("+id+",'"+message+"','"+messageReturn+"','"+groupName+"','"+groupId+"','"+author+"');");
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
            this.getLogger().info("���ݱ�<" + this.tableStd().getTableName() + ">�������");
        }else {
            try {
                this.getLogger().info("���ݱ�<" + this.tableStd().getTableName()+ ">�����ڣ����ڴ�����");
                this.connection.createStatement().execute("CREATE TABLE SPLICALREPLYTABLE(" +
                        "ID INT  NOT NULL," +
                        "MESSAGE  TEXT  NOT NULL," +
                        "RETURN  TEXT  NOT NULL," +
                        "GROUPNAME TEXT," +
                        "GROUPID INTEGER," +
                        "AUTHOR TEXT NOT NULL)");
                this.getLogger().info("���ݱ�<" + this.tableStd().getTableName() + ">�����ɹ�");
            } catch (SQLException e) {
                this.getLogger().error("���ݱ�<" + this.tableStd().getTableName() + ">����ʧ��", e);
            }
        }
    }
}
package xyz.satdg.sao.icaris.database;

import xyz.satdg.sao.icaris.api.bases.DbObject;
import xyz.satdg.sao.icaris.api.bases.TableBase;
import xyz.satdg.sao.icaris.api.marks.Table;
import xyz.satdg.sao.icaris.base.SpMessageStd;
import xyz.satdg.sao.icaris.base.TableStd;
import xyz.satdg.sao.icaris.core.DbSystem;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ����ظ���
 * @author GongSunink
 */
@Table(tableName = "SPLICALREPLYTABLE",dbName = "BotDB")
public class SPreplyTable extends TableBase {


    @Override
    public TableStd tableStd() {
        return new TableStd("SPLICALREPLYTABLE");
    }


    public boolean delete(String message){
        try{
            DbSystem.getGobalConnection().createStatement().executeUpdate(
                    "DELETE from SPLICALREPLYTABLE where MESSAGE ="+message);
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
            this.getLogger().error(e);
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
                 * ע��statementִ�н�����һ��Ҫ�رգ�������ܻ����SQL_BUSY Exception,����ֱ��ʹ����ʱ���󣬽�����
                 * ����ֱ�ӱ�����
                 */
            }catch (SQLException e){
                this.getLogger().error("��Ϣ��¼����ʧ��<" + this.tableStd().getTableName()+ ">",e);
            }
        }

    }

    @Override
    public void initTable() {
        if (DbSystem.isTableExsit(this.tableStd().getTableName())) {
            this.getLogger().info("���ݱ�<" + this.tableStd().getTableName() + ">�������");
        }else {
            try {
                this.getLogger().info("���ݱ�<" + this.tableStd().getTableName()+ ">�����ڣ����ڴ�����");
                DbSystem.getGobalConnection().createStatement().execute("CREATE TABLE SPLICALREPLYTABLE(" +
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
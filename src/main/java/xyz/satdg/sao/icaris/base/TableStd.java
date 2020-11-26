package xyz.satdg.sao.icaris.base;

import xyz.satdg.sao.icaris.api.bases.DbObject;

import java.sql.Connection;

/**
 * @author GongSunink
 */
public class TableStd extends DbObject {
    private String tableName;
    private Connection connection;

    public TableStd(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public TableStd(String tableName, Connection connection) {
        this.tableName = tableName;
        this.connection = connection;
    }
}

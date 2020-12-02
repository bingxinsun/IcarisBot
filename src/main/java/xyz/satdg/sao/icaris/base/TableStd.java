package xyz.satdg.sao.icaris.base;

import xyz.satdg.sao.icaris.api.bases.DbObject;

import java.sql.Connection;

/**
 * @author GongSunink
 */
public class TableStd {
    private String tableName;

    public TableStd(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public TableStd(String tableName, Connection connection) {
        this.tableName = tableName;
    }
}

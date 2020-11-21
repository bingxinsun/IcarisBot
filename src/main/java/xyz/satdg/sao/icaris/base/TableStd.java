package xyz.satdg.sao.icaris.base;

import xyz.satdg.sao.icaris.core.Mloger.MLoger;

/**
 * @author GongSunink
 */
public class TableStd {
    private String tableName;
    private MLoger mLoger = new MLoger();

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public MLoger getmLoger() {
        return mLoger;
    }

    public TableStd(String tableName, MLoger mLoger) {
        this.tableName = tableName;
        this.mLoger = mLoger;
    }

    public void setmLoger(MLoger mLoger) {
        this.mLoger = mLoger;
    }

    public String getTableName() {
        return tableName;
    }


    @Override
    public String toString() {
        return "TableStd{" +
                "tableName='" + tableName + '\'' +
                ", mLoger=" + mLoger +
                '}';
    }

    public TableStd(String tableName){
        this.tableName = tableName;
    }
}

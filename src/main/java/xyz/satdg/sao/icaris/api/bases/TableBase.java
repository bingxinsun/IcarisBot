package xyz.satdg.sao.icaris.api.bases;

import xyz.satdg.sao.icaris.api.DataTable;
import xyz.satdg.sao.icaris.api.DbObject;

/**
 * 数据库基础类
 * @author GongSunink
 */
public abstract class TableBase<T extends DbObject> implements DataTable<T> {
    enum TableState{
        NOT_FOUND,
        CREATING,
        CREATE_SUCCESS,

    }
}

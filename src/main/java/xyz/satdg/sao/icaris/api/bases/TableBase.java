package xyz.satdg.sao.icaris.api.bases;

import xyz.satdg.sao.icaris.api.DataTable;
import xyz.satdg.sao.icaris.core.Mloger.MLoger;


/**
 * 数据库基础类
 * @author GongSunink
 */
public abstract class TableBase implements DataTable {

    @Override
    public MLoger getLogger() {
        return new MLoger();
    }

}

package xyz.satdg.sao.icaris.api.bases;

import xyz.satdg.sao.icaris.api.DataTable;
import xyz.satdg.sao.icaris.core.Mloger.MLoger;
import xyz.satdg.sao.icaris.database.TableHelper;

import java.sql.Connection;


/**
 * ���ݿ������
 * @author GongSunink
 */
public abstract class TableBase implements DataTable {

    @Override
    public MLoger getLogger() {
        return new MLoger();
    }

}

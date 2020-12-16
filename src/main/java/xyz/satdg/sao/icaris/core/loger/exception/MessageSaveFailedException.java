package xyz.satdg.sao.icaris.core.loger.exception;

import xyz.satdg.sao.icaris.api.bases.TableBase;

import java.sql.SQLException;

/**
 * @author GongSunink
 */
public class MessageSaveFailedException extends SQLException {

    private TableBase tableBase;

    public MessageSaveFailedException(TableBase tableBase){
        this.tableBase = tableBase;
    }

    @Override
    public String getMessage() {
        return "message save failed at"+tableBase.tableStd().getTableName();
    }
}

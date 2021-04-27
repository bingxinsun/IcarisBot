package xyz.satdg.sao.icaris.core.loger.exception;

import xyz.satdg.sao.icaris.api.bases.TableBase;

import java.sql.SQLException;

/**
 * @author GongSunink
 */
public class MessageSaveFailedException extends SQLException {

    private TableBase<?> tableBase;
    private Throwable throwable;

    public MessageSaveFailedException(TableBase<?> tableBase, Throwable throwable) {
        this.tableBase = tableBase;
        this.throwable = throwable;
    }

    @Override
    public synchronized Throwable getCause() {
        return throwable;
    }

    @Override
    public String getMessage() {
        return "message save failed at" + tableBase.tableStd().getTableName();
    }
}

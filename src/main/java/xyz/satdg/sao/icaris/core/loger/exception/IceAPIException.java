package xyz.satdg.sao.icaris.core.loger.exception;

import javafx.scene.control.IndexedCell;

/**
 * @author GongSunink
 */
public class IceAPIException extends RuntimeException{

    public IceAPIException(Throwable throwable){
        super(throwable);
    }

    @Override
    public String getMessage() {
        return "ice api return null message";
    }
}

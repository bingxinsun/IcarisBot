package xyz.satdg.sao.icaris.core.loger.exception;

/**
 * @author GongSunink
 */
public class PackageScannerException extends RuntimeException{

    public PackageScannerException(Throwable throwable){
        super(throwable);
    }

    @Override
    public String getMessage() {
        return "package scanner exception";
    }
}

package innercore.systemscheduler.loader;

/**
 * @author GongSunink
 */
public interface ActiveLoader {
    /**
     * the start method of the system
     * scheduler will invoke this method
     */
    void start();

    /**
     * this method drops system
     */
    boolean drop();

}
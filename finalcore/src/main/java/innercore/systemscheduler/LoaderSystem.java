package innercore.systemscheduler;

/**
 * a system interface ,all system on scheduler need to implement this interface
 *
 * @author GongSunink
 */
public interface LoaderSystem {

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

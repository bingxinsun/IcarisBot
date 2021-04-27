package loaders;

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
     *
     * @return this return which the func running state
     */
    boolean drop();

}

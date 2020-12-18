package innercore.systemscheduler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author GongSunink
 */
@Target(ElementType.TYPE)
public @interface Loader {

    String name() default "N/A";

    /**
     * Loader
     * ObserverLoader
     * EventLoader
     * CommandLoader
     */
    enum LoaderType{
        SYSTEMLOADER,
        OBSERVERLOADER,
        EVENTLOADER,
        COMMANDLOADER,
    }

    LoaderType type();
}

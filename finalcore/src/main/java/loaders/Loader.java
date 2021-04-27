package loaders;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author GongSunink
 */
@Target(ElementType.TYPE)
public @interface Loader {

    String name() default "N/A";

    enum LoaderType{
        /**
         * passive loader means that this loader cannot be directly start by the scheduler, it must
         * start by other system
         * <p>
         * active loader means that this loader can be directly start by the scheduler
         */
        PASSIVE_LOADER,
        ACTIVE_LOADER,
    }

    LoaderType type();
}

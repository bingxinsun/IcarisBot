package xyz.satdg.sao.icaris.api.marks;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author GongSunink
 */
@Target(ElementType.TYPE)
public @interface SystemLoader {

    String name() default "N/A";

    enum LoaderType{
        SYSTEMLOADER,
        OBSERVERLOADER,
        EVENTLOADER,
        COMMANDLOADER,
    }

    LoaderType type();
}

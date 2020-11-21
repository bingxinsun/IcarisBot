package xyz.satdg.sao.icaris.api.marks;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 数据表操作方法标记
 * @author GongSunink
 */
@Target(ElementType.METHOD)
public @interface TableActions {

    /**
     * 数据表操作方法的标记
     */
    enum actionType{
        INSERT,
        SELECT,
        DELETE,
        UPDATE
    }

    String TableName();

    actionType value();
}

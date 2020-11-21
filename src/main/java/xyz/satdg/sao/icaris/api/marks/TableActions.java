package xyz.satdg.sao.icaris.api.marks;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * ���ݱ�����������
 * @author GongSunink
 */
@Target(ElementType.METHOD)
public @interface TableActions {

    /**
     * ���ݱ���������ı��
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

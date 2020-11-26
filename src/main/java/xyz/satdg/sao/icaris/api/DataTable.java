package xyz.satdg.sao.icaris.api;


import xyz.satdg.sao.icaris.base.TableStd;
import xyz.satdg.sao.icaris.core.Mloger.MLoger;

import java.sql.Connection;

/**
 * 数据库接口
 * @author GongSunink
 */
public interface DataTable {

    /**
     * 获得日志器
     * @return 日志器
     */
    MLoger getLogger();

    /**
     * 获得数据库对象
     * @return 数据库对象
     */
    TableStd tableStd();

    /**
     * 初始化数据表
     * ->检查数据表是否正常
     */
    void initTable();
}

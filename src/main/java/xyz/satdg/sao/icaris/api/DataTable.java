package xyz.satdg.sao.icaris.api;


import xyz.satdg.sao.icaris.api.bases.DbObject;
import xyz.satdg.sao.icaris.base.TableStd;
import xyz.satdg.sao.icaris.core.Mloger.MLoger;

import java.sql.Connection;

/**
 * 数据库接口
 * @author GongSunink
 */
public interface DataTable {

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

    /**
     * 插入函数
     * @param object 数据
     */
    void insert(DbObject object);

    /**
     * 选择函数，返回选择的对象
     * @param object 需要选择的对象
     * @return 对象
     */
    DbObject select(DbObject object);
}

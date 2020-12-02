package xyz.satdg.sao.icaris.api;


import xyz.satdg.sao.icaris.api.bases.DbObject;
import xyz.satdg.sao.icaris.base.TableStd;
import xyz.satdg.sao.icaris.core.Mloger.MLoger;

import java.sql.Connection;

/**
 * ���ݿ�ӿ�
 * @author GongSunink
 */
public interface DataTable {

    /**
     * ������ݿ����
     * @return ���ݿ����
     */
    TableStd tableStd();

    /**
     * ��ʼ�����ݱ�
     * ->������ݱ��Ƿ�����
     */
    void initTable();

    /**
     * ���뺯��
     * @param object ����
     */
    void insert(DbObject object);

    /**
     * ѡ����������ѡ��Ķ���
     * @param object ��Ҫѡ��Ķ���
     * @return ����
     */
    DbObject select(DbObject object);
}

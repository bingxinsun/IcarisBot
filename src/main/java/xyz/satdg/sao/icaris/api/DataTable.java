package xyz.satdg.sao.icaris.api;


import xyz.satdg.sao.icaris.base.TableStd;
import xyz.satdg.sao.icaris.core.Mloger.MLoger;

import java.sql.Connection;

/**
 * ���ݿ�ӿ�
 * @author GongSunink
 */
public interface DataTable {

    /**
     * �����־��
     * @return ��־��
     */
    MLoger getLogger();

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
}

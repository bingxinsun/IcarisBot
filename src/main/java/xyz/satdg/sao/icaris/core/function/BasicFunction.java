package xyz.satdg.sao.icaris.core.function;

/**
 * һЩ�������ܣ���ȡ�������ת��hashֵ��ת��base64��
 * @author GongSunink
 */
public class BasicFunction {
    /**
     * System.currentTimeMillis() ���ش�1970��1��1�տ�ʼ�����ڵĺ�������ΪLong����
     * �Է��ص�ֵǿֵ��������[min,max]������
     * @param max תint���ͣ�Ȼ��ȡ���������Сֵ�Ĳ�ֵ���ټ�����Сֵ��ȡ��α�����
     *      * %(max-min)Ϊ�����Ͻ��ȥ�½磬����ʹ�û���������½�֮�󲻻�Խ���Ͻ�
     * @param min �����½�
     * @return ���������
     */
    public static int getRand(int max,int min){
        return (int)System.currentTimeMillis()%(max-min)+min;
    }
}
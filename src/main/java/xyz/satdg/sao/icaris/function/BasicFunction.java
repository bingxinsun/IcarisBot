package xyz.satdg.sao.icaris.function;

/**
 * һЩ�������ܣ���ȡ�������ת��hashֵ��ת��base64��
 * @author GongSunink
 */
public class BasicFunction {
    /**
     * System.currentTimeMillis() ���ش�1970��1��1�տ�ʼ�����ڵĺ�������ΪLong����
     * �Է��ص�ֵǿֵ��������[min,max]������
     * @param max תint���ͣ�Ȼ��ȡ���������Сֵ�Ĳ�ֵ���ټ�����Сֵ��ȡ��α�����
     *      * %(max-min)�������Ͻ�
     * @param min �����½�
     * @return ���������
     */
    public static int getRand(int max,int min){
        return (int)System.currentTimeMillis()%(max-min)+min;
    }


}

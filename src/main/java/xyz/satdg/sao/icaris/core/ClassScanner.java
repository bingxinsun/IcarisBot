package xyz.satdg.sao.icaris.core;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * ��ɨ����
 * @author GongSunink
 */
public class ClassScanner {

    /**
     * ��Ŀ������class�����
     * 1.ɨ����µ��ļ�
     * 2.ͨ��Class.forName���Ի��Ŀ����Ķ���ʧ��������
     * @param packageName Ŀ���
     * @return set���ϣ���������ļ�Ϊ���򷵻�null
     * @throws Exception ����
     */
    public static Set<Class<?>> getClasses(String packageName) throws Exception{
        Set<Class<?>> classSet = new HashSet<>();
        File dir = new File("src"+File.separator+"main"+File.separator+"java"+File.separator+packageName.replace(".",File.separator));
        System.out.println(dir.toPath());
        if (dir.getCanonicalFile().isDirectory()) {
            System.out.println(dir.getName());
            File[] files = dir.listFiles();
            if (files!=null&&files.length!=0){
                System.out.println(Arrays.toString(files));
                for (File file : files) {
                    System.out.println(file.getName());
                    String className = file.getName();
                    Class targetClass = Class.forName(packageName + "." + className.split(File.separator)[0]);
                    classSet.add(targetClass);
                }
            }
        }else {
            System.out.println(false);
            return null;
        }
        return classSet;
    }
}

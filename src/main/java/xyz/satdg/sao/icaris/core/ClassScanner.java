package xyz.satdg.sao.icaris.core;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * ��ɨ����
 * @author GongSunink
 */
public class ClassScanner {


    /**
     * ɨ��ָ����
     * @param packageName ����
     * @return ���µ��༯��
     * @throws IOException ����
     */
    public static Set<Class<?>> scanPackage(String packageName) throws IOException,ClassNotFoundException {
        Set<Class<?>> classSet = new HashSet<>();
        String path = ClassScanner.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        //��ô������ڵľ���·������Ϊjar��ʱ�᷵��jar����
        if (System.getProperty("os.name").contains("dows")&&!path.contains(".jar")) {
            //���·���в�����.jar,��ϵͳ���ư���dows,˵�����ڵ��Ի�����,ֱ�ӽ���ɨ��
            classSet=getClasses(packageName);
        }
        else if (path.contains(".jar")) {
            //���·���д���.jar��˵�����е�ʱ�ڴ������ʱ��Ҫʹ��Jarfile�������ж�ȡ
            File file = new File(path);
            classSet=getClasses(new JarFile(file.getName()),packageName);
        }
        return classSet;
    }

    /**
     * �ڿ��������н���ɨ��
     * @param targetPackage
     * @return
     */
    public static Set<Class<?>> getClasses(String targetPackage)throws ClassNotFoundException{
        Set<Class<?>> classSet = new HashSet<>();
        String path = ClassScanner.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        //���Դ���ļ���λ��
        File dir = new File(path+targetPackage.replace(".","/"));
        //����file����
        if (dir.isDirectory()) {
            //���Ŀ��������ļ���ִ��
            File[] files = dir.listFiles();
            for (File file : files) {
                //ʹ��forecah���б���
                String className = file.getName();
                Class targetClass = Class.forName(targetPackage+ "." + className.split("\\.")[0]);
                //���Ի�ȡ�����
                classSet.add(targetClass);
            }
        }
        return classSet;
    }

    /**
     * ͨ��jar�ļ����Ŀ����µ����б�
     * @param file jar�ļ�
     * @param targetPackage Ŀ���
     * @return
     */
    public static Set<Class<?>> getClasses(JarFile file,String targetPackage) throws ClassNotFoundException{
        Set<Class<?>> classSet = new HashSet<>();
        Enumeration<JarEntry> entry= file.entries();
        while(entry.hasMoreElements()){
            //�Ƿ��и�����ļ�Ԫ��
            JarEntry element= entry.nextElement();
            if (element.getName().replace("/",".").contains(targetPackage) &&element.getName().endsWith(".class")){
                Class targetClass = Class.forName(targetPackage +
                        "."+element.getName().substring(element.getName().
                        lastIndexOf("/")+1, element.getName().lastIndexOf(".")));
                    //��ȡxxxx.class�е�xxxx
                classSet.add(targetClass);
            }
        }
        return classSet;
    }
}

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
     * scan target package
     * @param packageName ����
     * @return ���µ��༯��
     * @throws IOException ����
     */
    public static Set<Class<?>> scanPackage(String packageName) throws IOException,ClassNotFoundException {
        Set<Class<?>> classSet = new HashSet<>();
        String path = ClassScanner.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        //get the absolute path of the package
        if (System.getProperty("os.name").contains("dows")&&!path.contains(".jar")) {
            //if path contain "dows" and dose not contain jar,define it in a develop environment
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
     * scan in a develop environment
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
     * 1.JarFile-Reader is a base-on ZipFile-Reader,Use it to read Jar-file lists
     * 2.logic as same as upon
     * @param file jar�ļ�
     * @param targetPackage Ŀ���
     * @return
     */
    public static Set<Class<?>> getClasses(JarFile file,String targetPackage) throws ClassNotFoundException{
        Set<Class<?>> classSet = new HashSet<>();
        Enumeration<JarEntry> entry= file.entries();
        while(entry.hasMoreElements()){
            //are there more elements
            JarEntry element= entry.nextElement();
            if (element.getName().replace("/",".").contains(targetPackage) &&element.getName().endsWith(".class")){
                Class targetClass = Class.forName(targetPackage +
                        "."+element.getName().substring(element.getName().
                        lastIndexOf("/")+1, element.getName().lastIndexOf(".")));
                    //get xxx of xxx.class
                classSet.add(targetClass);
            }
        }
        return classSet;
    }
}

package xyz.satdg.sao.icaris.core;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 类扫描器
 * @author GongSunink
 */
public class ClassScanner {


    /**
     * scan target package
     * @param packageName 包名
     * @return 包下的类集合
     * @throws IOException 错误
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
            //如果路径中存在.jar，说明运行的时在打包后，这时需要使用Jarfile类来进行读取
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
        //获得源码文件的位置
        File dir = new File(path+targetPackage.replace(".","/"));
        //创建file对象
        if (dir.isDirectory()) {
            //如果目标包下有文件则执行
            File[] files = dir.listFiles();
            for (File file : files) {
                //使用forecah进行遍历
                String className = file.getName();
                Class targetClass = Class.forName(targetPackage+ "." + className.split("\\.")[0]);
                //尝试获取类对象
                classSet.add(targetClass);
            }
        }
        return classSet;
    }

    /**
     * 1.JarFile-Reader is a base-on ZipFile-Reader,Use it to read Jar-file lists
     * 2.logic as same as upon
     * @param file jar文件
     * @param targetPackage 目标包
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

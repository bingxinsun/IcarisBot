package xyz.satdg.sao.icaris.core;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * class scanner
 * @author GongSunink
 */
public class ClassScanner {

    public static Set<Class<?>> scanPackage(String packageName) throws IOException,ClassNotFoundException{
        Set<Class<?>> classSet = new HashSet<>(20);
        String path = ClassScanner.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        //get the absolute path of the package
        if (System.getProperty("os.name").contains("dows")&&!path.contains(".jar")) {
            //if path contain "dows" and dose not contain jar,define it in a develop environment
            getClasses(path+packageName.replace(".","/"),
                    packageName,classSet);
        }
        else if (path.contains(".jar")) {
            //if path contain "jar"
            File file = new File(path);
            classSet = getClasses(new JarFile(file.getName()),packageName);
        }
        return classSet;
    }

    /**
     * scan target package(package dose not has subPackages)
     * @param packageName package name
     * @return a ClassesSet under target package
     * @throws IOException 错误
     */
    public static Set<Class<?>> scanSinglePackage(String packageName) throws IOException,ClassNotFoundException {
        Set<Class<?>> classSet = new HashSet<>();
        String path = ClassScanner.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        //get the absolute path of the package
        if (System.getProperty("os.name").contains("dows")&&!path.contains(".jar")) {
            //if path contain "dows" and dose not contain jar,define it in a develop environment
            classSet=getClasses(packageName);
        }
        else if (path.contains(".jar")) {
            //if path contain "jar"
            File file = new File(path);
            classSet=getClasses(new JarFile(file.getName()),packageName);
        }
        return classSet;
    }

    /**
     * scan in a develop environment
     * @param targetPackage package need to scan
     * @return a ClassSet with classes under target package
     */
    private static Set<Class<?>> getClasses(String targetPackage)throws ClassNotFoundException{
        Set<Class<?>> classSet = new HashSet<>();
        String path = ClassScanner.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        //get the path of target class(int windows file system and in a develop environment)
        File dir = new File(path+targetPackage.replace(".","/"));
        if (dir.isDirectory()) {    
            File[] files = dir.listFiles();
            if (files!=null){
                for (File file : files) {
                    //using for-each statement to iterate files list
                    String className = file.getName();
                    Class targetClass = Class.forName(targetPackage+ "." + className.split("\\.")[0]);
                    //try to get instance of the Class
                    classSet.add(targetClass);
                }
            }

        }
        return classSet;
    }
    /**
     * 1.JarFile-Reader is base-on ZipFile-Reader,Use it to read Jar-file lists
     * 2.logic as same as upon
     * @param file jar文件
     * @param targetPackage 目标包
     * @return classSet
     */
    private static Set<Class<?>> getClasses(JarFile file,String targetPackage) throws ClassNotFoundException{
        Set<Class<?>> classSet = new HashSet<>();
        Enumeration<JarEntry> entry= file.entries();
        while(entry.hasMoreElements()){
            //are there more elements
            JarEntry element= entry.nextElement();
            if (element.getName().replace("/",".").contains(targetPackage)
                    &&element.getName().endsWith(".class")){
                Class targetClass = Class.forName(targetPackage +
                        "."+element.getName().substring(element.getName().
                        lastIndexOf("/")+1, element.getName().lastIndexOf(".")));
                    //get xxx of xxx.class
                classSet.add(targetClass);

            }
        }
        return classSet;
    }

    /**
     * get file names under the target package,and add them to a List
     * @param path 扫描路径
     * @param classSet 返回的类对象集合
     */
    private static void getClasses(String path, String packageName, Set<Class<?>> classSet) {
        File file = new File(path);
        if (file.isDirectory()){
            File[] files = file.listFiles();
            if (files!=null){
                for (File value : files) {
                    //it iterates packageName plus the dir file name as the father packageName of class
                    getClasses(value.getPath(), packageName + "." +
                            value.getName(), classSet);
                }
            }
        }else {
            if (file.getName().endsWith(".class")){
                String classNameSps = packageName.substring(0,packageName.lastIndexOf("."));
                //using for-each statement to iterate files list
                try {
                    Class targetClass = Class.forName(classNameSps);
                    //try to get instance of the Class
                    classSet.add(targetClass);
                }catch (ClassNotFoundException e){
                    System.out.println(classNameSps+"未找到");
                }
            }
        }
    }

//    private static void getClasses(JarFile file){
//        Set<Class<?>> classSet = new HashSet<>();
//        Enumeration<JarEntry> entry= file.entries();
//        while(entry.hasMoreElements()){
//            JarEntry fileEntry = entry.nextElement();
//            if (fileEntry.getName().endsWith(".class")){
//                try{
//                    Class targetClass = Class.forName(fileEntry.getName().replace(
//                            "\\","."
//                    ).substring(0,fileEntry.getName().lastIndexOf(".")));
//                }catch (ClassNotFoundException e){
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//    }
}

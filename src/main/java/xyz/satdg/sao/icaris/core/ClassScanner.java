package xyz.satdg.sao.icaris.core;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 类扫描器
 * @author GongSunink
 */
public class ClassScanner {

    /**
     * 从目标包获得class对象表
     * 1.扫描包下的文件
     * 2.通过Class.forName尝试获得目标类的对象，失败则跳过
     * @param packageName 目标包
     * @return set集合，如果包下文件为空则返回null
     * @throws Exception 错误
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

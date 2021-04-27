package xyz.satdg.sao.icaris.core;

import java.io.IOException;

/**
 * @author GongSunink
 */
public class LoadAll {
    public static void start(){
        try{
           for (Class<?> c: ClassScanner.scanFullPackage("xyz.satdg.sao.icaris")){
                System.out.println(c.getName());
            }
        }catch (ClassNotFoundException| IOException e){
            e.printStackTrace();
        }

    }

}

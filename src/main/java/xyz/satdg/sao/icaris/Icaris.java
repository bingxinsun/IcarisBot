package xyz.satdg.sao.icaris;

import xyz.satdg.sao.icaris.core.IcarisBotSystem;

import java.io.*;

/**
 * Æô¶¯Èë¿Ú
 * @author GongSunink
 */
public class Icaris {

    public static void init() throws IOException {
        try {
            StringBuffer buffer = new StringBuffer();
            String temp;
            BufferedReader reader = new BufferedReader(new FileReader("banner.txt"));
            while ((temp=reader.readLine())!=null){
                buffer.append(temp+'\n');
            }
            System.out.println(buffer);
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        IcarisBotSystem.start();

    }
}
package xyz.satdg.sao.icaris;

import xyz.satdg.sao.icaris.bot.IcarisBot;

import java.io.*;

/**
 * Æô¶¯Èë¿Ú
 * @author GongSunink
 */
public class Icaris {

    public static void init() {
        try {
            StringBuffer buffer = new StringBuffer();
            String temp;
            BufferedReader reader = new BufferedReader(new FileReader("banner.txt"));
            while ((temp=reader.readLine())!=null){
                buffer.append(temp+'\n');
//                Thread.sleep(100);
//                System.out.println(temp);
            }
            System.out.println(buffer);
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        IcarisBot.startBot(2662870569L,"iam33233");

    }
}

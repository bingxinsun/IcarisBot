package xyz.satdg.sao.icaris;


import xyz.satdg.sao.icaris.core.IcarisBotSystem;
import xyz.satdg.sao.icaris.core.LoadAll;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author GongSunink
 */
public class Matrix {
    public static void main(String[] args) throws IOException {
        StringBuffer buffer = new StringBuffer();
        String temp;
        BufferedReader reader = new BufferedReader(new FileReader("banner.txt"));
        while ((temp=reader.readLine())!=null){
            buffer.append(temp).append('\n');
        }
        System.out.println(buffer);
        reader.close();
       // LoadAll.start();

        IcarisBotSystem.start();
    }
}

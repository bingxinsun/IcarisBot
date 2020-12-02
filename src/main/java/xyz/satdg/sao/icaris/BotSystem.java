package xyz.satdg.sao.icaris;


import kotlinx.serialization.json.JsonObject;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author GongSunink
 */
public class BotSystem {
    public static void start() throws IOException  {
        StringBuffer buffer = new StringBuffer();
        String temp;
        BufferedReader reader = new BufferedReader(new FileReader("botConfig.json"));
        while ((temp=reader.readLine())!=null){
            buffer.append(temp);
        }
        reader.close();
        JSONObject jsonObject = JSONObject.fromObject(buffer.toString());
        System.out.println(jsonObject.toString());
    }
}

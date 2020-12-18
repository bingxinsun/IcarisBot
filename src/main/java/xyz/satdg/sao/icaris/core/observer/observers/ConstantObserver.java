package xyz.satdg.sao.icaris.core.observer.observers;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.message.GroupMessageEvent;
import net.mamoe.mirai.message.MessageEvent;
import net.sf.json.JSONObject;
import xyz.satdg.sao.icaris.api.bases.ObserverBase;
import xyz.satdg.sao.icaris.base.EventListenerGroupStd;
import xyz.satdg.sao.icaris.core.loger.exception.IceAPIException;
import xyz.satdg.sao.icaris.core.observer.SaveMessages;
import static xyz.satdg.sao.icaris.core.IcarisBotSystem.ICARIS_LOGGER;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 *常监听器，优先级最高，不能打断或是取消
 * @author GongSunink
 */
public class ConstantObserver extends ObserverBase {

    @Override
    public EventListenerGroupStd listenerStd() {
        return new EventListenerGroupStd(
                "常监听器", ObserverType.CONSTANT);
    }

    private boolean firstThread = true;

    private long timeNow;

    /**
     * 记录消息以及回复消息（通过小冰api）暂时，标注synchronized避免多线程占用，同时原子化操作防止2次引用
     * @param event 消息事件
     */
//    @EventHandler(priority = Listener.EventPriority.HIGHEST)
//    public ListeningStatus constantMessageListener(MessageEvent event){
//        if (firstThread &&IcarisSystem.currentTimeMillis()-timeNow >= 5000){
//            timeNow=IcarisSystem.currentTimeMillis();
//            firstThread =false;
//            try{
//                String message = event.getMessage().contentToString();
//                URL url = new URL("http://127.0.0.1:6789/chat?"+"text="+
//                        URLEncoder.encode(message,"utf-8")+"&type=text");
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.connect();
//                // 取得输入流，并使用Reader读取
//                BufferedReader reader = new BufferedReader(
//                        new InputStreamReader( connection.getInputStream(), StandardCharsets.UTF_8));
//                //reader接受输入流
//                StringBuffer sb = new StringBuffer();
//                String line = "";
//                while ((line = reader.readLine()) != null) {
//                    sb.append(line);
//                }
//                connection.disconnect();
//                reader.close();
//                // 断开连接
//                String sub=sb.toString();
//                //sub=sub.split(":")[2];
//                JSONObject json= JSONObject.fromObject(sub);
//                if (json.get("text")!=" "){
////                    event.getSubject().sendMessage(json.get("text").toString().replace(
////                            "\n","")+"da"+
////                            URLDecoder.decode("\u2b50","unicode")+"ze~");
//                    event.getSubject().sendMessage(json.get("text").toString().
//                            replace("\n","").trim()/*+"da⭐ze~"*/);
//                }else {
//                    tryAnother(event);
//                }
//                /*
//                   Java中显式字符串都是操作系统的编码格式来进行编码的，而Jvm内部是完全使用Unicode的,所以Java在
//                   编译时需要将字符串转换成Unicode然后再进入JVM进行处理，于是这里便出现了问题：显式字符串的编码都是
//                   操作系统的编码格式，编译器默认将字符串们看做是操作系统编码格式的字符串，于是进行映射，但这时如果
//                   编辑器使用的格式不是操作系统编码格式，那么就会出现无法映射的错误，那么在输出时就会变成乱码，如果需要修改
//                   默认的编码格式，则需要以下两步1.修改操作系统编码格式2.在编辑器的vmOpinions中加入参数
//                   -Dfile.encoding=utf-8,才能正确解析,如果不是显式的字符串，都是在java运行期间处理的，也就是说
//                   在Jvm中处理的，这样就可以将输入或是输出的字符串转变到目标类型
//                 */
//                firstThread =true;
//            }catch (IOException |IllegalArgumentException e){
//                firstThread =true;
//                try {
//                    tryAnother(event);
//                }catch (IOException |IllegalArgumentException e2){
//                    ICARIS_LOGGER.error("retrying...",new IceAPIException(e2));
//                    constantMessageListener(event);
//                }
//                ICARIS_LOGGER.error("retrying...",new IceAPIException(e));
//            }
//        }
//        SaveMessages.save(event.getSender().getId(),event.getMessage().contentToString(),
//                event.getSender().getNick(),(event instanceof GroupMessageEvent) ? ((GroupMessageEvent)
//                        event).getGroup().getName():"NULL",(event instanceof GroupMessageEvent) ?
//                        ((GroupMessageEvent) event).getGroup().getId():0);
//        return ListeningStatus.LISTENING;
//    }


    private void tryAnother(MessageEvent event) throws IOException {
        String message = event.getMessage().contentToString();
        URL url = new URL("http://127.0.0.1:6790/chat?"+"text="+
                URLEncoder.encode(message,"utf-8")+"&type=text");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        // 取得输入流，并使用Reader读取
        BufferedReader reader = new BufferedReader(
                new InputStreamReader( connection.getInputStream(), StandardCharsets.UTF_8));
        //reader接受输入流
        StringBuffer sb = new StringBuffer();
        String line = "";
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        connection.disconnect();
        reader.close();
        // 断开连接
        String sub=sb.toString();
        //sub=sub.split(":")[2];
        JSONObject json= JSONObject.fromObject(sub);
        if (json.get("text")!=" "){
//                    event.getSubject().sendMessage(json.get("text").toString().replace(
//                            "\n","")+"da"+
//                            URLDecoder.decode("\u2b50","unicode")+"ze~");
            event.getSubject().sendMessage(json.get("text").toString().
                    replace("\n","").trim()/*+"da⭐ze~"*/);
        }
    }
}

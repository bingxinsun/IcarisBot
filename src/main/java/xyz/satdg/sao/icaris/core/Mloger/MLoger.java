package xyz.satdg.sao.icaris.core.Mloger;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.utils.MiraiLoggerPlatformBase;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

/**
 * ×Ô¶¨Òåloger
 * @author GongSunink
 */
public class MLoger extends MiraiLoggerPlatformBase {
    private Bot bot;
    private String botName;

    public MLoger(Bot bot,String botName){
        this.bot=bot;
        this.botName=botName;
    }

    public MLoger(){
        this(null,"Gobal");
    }

    @Override
    public void debug0(@Nullable String s, @Nullable Throwable throwable) {
        String logmessage=botName+"-=>D=>"+s+"->"+new Date().toString();
        System.out.println(logmessage);
//        System.out.println("\33[32;0m"+logmessage+"\33[0m");
    }

    @Override
    public void error0(@Nullable String s, @Nullable Throwable throwable) {
        String logmessage=botName+"-=>E=>"+s+">>"+((throwable.getMessage() != null) ?
                throwable.getMessage() : throwable.getCause())
                +"->"+new Date().toString();
        System.out.println(logmessage);
//        System.out.println("\33[31;0m"+logmessage+"\33[0m");
    }

    @Override
    public void info0(@Nullable String s, @Nullable Throwable throwable) {
        String logmessage=botName+"-=>I=>"+s+"->"+new Date().toString();
        System.out.println(logmessage);
//        System.out.println("\33[34;0m"+logmessage+"\33[0m");
    }

    @Override
    public void verbose0(@Nullable String s, @Nullable Throwable throwable) {
        String logmessage=botName+"-=>V=>"+s+"->"+new Date().toString();
        if (s.contains("Send")){
            logmessage=botName+"-=>V=>"+s+"->"+new Date().toString();
        }
        else if(s.contains("Recv")){
            logmessage=botName+"-=>V<="+s+"->"+new Date().toString();
        }

        System.out.println(logmessage);
        //System.out.println("\33[32;0m"+logmessage+"\33[0m");
    }

    @Override
    protected void warning0(@Nullable String s, @Nullable Throwable throwable) {
        String logmessage=botName+"-=>W=>"+s+"->"+new Date().toString();
        System.out.println(logmessage);
//        System.out.println("\33[33;0m"+logmessage+"\33[0m");
    }
    @Nullable
    @Override
    public String getIdentity() {
        return "Bot_Marisa";
    }


}

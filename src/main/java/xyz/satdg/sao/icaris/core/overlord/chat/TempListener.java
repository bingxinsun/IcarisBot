package xyz.satdg.sao.icaris.core.overlord.chat;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.message.MessageEvent;
import xyz.satdg.sao.icaris.api.EventListenerType;
import xyz.satdg.sao.icaris.api.bases.EventListenerBase;
import xyz.satdg.sao.icaris.base.EventListenerGroupStd;
import xyz.satdg.sao.icaris.core.Mloger.MLoger;



/**a
 * @author GongSunink
 */
public class TempListener extends EventListenerBase {

    ListeningStatus listeningStatus = ListeningStatus.LISTENING;
    MLoger mLoger = new MLoger();

    @Override
    public EventListenerGroupStd listenerStd() {
        return new EventListenerGroupStd("ÁÙÊ±¼àÌýÆ÷×é", EventListenerType.TEMP);
    }

    /**
     * Class caller;
     *     String callerMethod;
     *     public TempListener(Class caller,String callerMethod){
     *         this.caller=caller;
     *         this.callerMethod=callerMethod;
     *         Events.registerEvents(this);
     *     }
     *
     *     private void returnToCaller(String message){
     *         try{
     *             this.caller.getMethod(this.callerMethod).invoke(null);
     *         }catch (Exception e){
     *             mLoger.error(e);
     *         }
     *     }
     */


    public void disable(){
        this.listeningStatus= ListeningStatus.STOPPED;
    }

    @EventHandler(priority = Listener.EventPriority.HIGHEST)
    public ListeningStatus tempListener(MessageEvent event){
        return this.listeningStatus;
    }

}

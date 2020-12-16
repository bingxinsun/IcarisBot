package xyz.satdg.sao.icaris.base;

import xyz.satdg.sao.icaris.api.DbObject;

/**
 * @author GongSunink
 */
public class SpMessageStd extends MessageStd implements DbObject {
    private String returnMessage;

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public SpMessageStd(long senderId, String message, String senderNick, String groupName, long grouopId, String returnMessage) {
        super(senderId, message, senderNick, groupName, grouopId);
        this.returnMessage = returnMessage;
    }
}

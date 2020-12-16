package xyz.satdg.sao.icaris.base;

import xyz.satdg.sao.icaris.api.DbObject;

/**
 * @author GongSunink
 */
public class MessageStd implements DbObject {

    private long senderId;
    private String message;
    private String senderNick;
    private String groupName;
    private long grouopId;

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderNick() {
        return senderNick;
    }

    public void setSenderNick(String senderNick) {
        this.senderNick = senderNick;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public long getGrouopId() {
        return grouopId;
    }

    public void setGrouopId(long grouopId) {
        this.grouopId = grouopId;
    }

    public MessageStd(long senderId, String message, String senderNick, String groupName
            , long grouopId) {
        this.senderId = senderId;
        this.message = message;
        this.senderNick = senderNick;
        this.groupName = groupName;
        this.grouopId = grouopId;
    }

    @Override
    public String toString() {
        return "MessageStd{" +
                "senderId=" + senderId +
                ", message='" + message + '\'' +
                ", senderNick='" + senderNick + '\'' +
                ", groupName='" + groupName + '\'' +
                ", grouopId=" + grouopId +
                '}';
    }
}

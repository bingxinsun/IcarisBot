package xyz.satdg.sao.icaris.base;

/**
 * @author GongSunink
 */
public class StydyMessageStd {
    private long senderId;
    private String message;
    private String messageReturn;
    private String senderNick;
    private String groupName;
    private long groupId;

    @Override
    public String toString() {
        return "StydyMessageStd{" +
                "senderId=" + senderId +
                ", message='" + message + '\'' +
                ", messageReturn='" + messageReturn + '\'' +
                ", senderNick='" + senderNick + '\'' +
                ", groupName='" + groupName + '\'' +
                ", groupId=" + groupId +
                '}';
    }

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

    public String getMessageReturn() {
        return messageReturn;
    }

    public void setMessageReturn(String messageReturn) {
        this.messageReturn = messageReturn;
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

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public StydyMessageStd(long senderId, String message, String messageReturn, String senderNick, String groupName, long groupId) {
        this.senderId = senderId;
        this.message = message;
        this.messageReturn = messageReturn;
        this.senderNick = senderNick;
        this.groupName = groupName;
        this.groupId = groupId;
    }


}

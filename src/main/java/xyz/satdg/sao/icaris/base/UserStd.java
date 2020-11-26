package xyz.satdg.sao.icaris.base;

/**
 * @author GongSunink
 */
public class UserStd {
    private long QQid;
    private String Nick;

    public UserStd(long QQid, String nick) {
        this.QQid = QQid;
        Nick = nick;
    }

    public long getQQid() {
        return QQid;
    }

    public void setQQid(long QQid) {
        this.QQid = QQid;
    }

    public String getNick() {
        return Nick;
    }

    @Override
    public String toString() {
        return "UserStd{" +
                "QQid=" + QQid +
                ", Nick='" + Nick + '\'' +
                '}';
    }

    public void setNick(String nick) {
        Nick = nick;
    }
}
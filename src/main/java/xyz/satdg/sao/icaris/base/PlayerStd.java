package xyz.satdg.sao.icaris.base;

/**
 * @author GongSunink
 */
public class PlayerStd {
    private long id;
    private String nick;
    private String callname;
    private int fellings;
    private int level;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PlayerStd(long id, String nick, String callname, int fellings, int level) {
        this.id = id;
        this.nick = nick;
        this.callname = callname;
        this.fellings = fellings;
        this.level = level;
    }

    @Override
    public String toString() {
        return "PlayerStd{" +
                "id=" + id +
                ", nick='" + nick + '\'' +
                ", callname='" + callname + '\'' +
                ", fellings=" + fellings +
                ", level=" + level +
                '}';
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getCallname() {
        return callname;
    }

    public void setCallname(String callname) {
        this.callname = callname;
    }

    public int getFellings() {
        return fellings;
    }

    public void setFellings(int fellings) {
        this.fellings = fellings;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

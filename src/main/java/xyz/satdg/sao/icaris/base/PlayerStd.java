package xyz.satdg.sao.icaris.base;


/**
 * @author GongSunink
 */
public class PlayerStd extends UserStd {
    private String callName;
    private int level;
    private int EXP;

    @Override
    public String toString() {
        return "PlayerStd{" +
                "callName='" + callName + '\'' +
                ", level=" + level +
                ", EXP=" + EXP +
                '}';
    }

    public PlayerStd(long QQid, String nick, String callName, int level, int EXP) {
        super(QQid, nick);
        this.callName = callName;
        this.level = level;
        this.EXP = EXP;
    }

    public int getEXP() {
        return EXP;
    }

    public void setEXP(int EXP) {
        this.EXP = EXP;
    }

    public String getCallName() {
        return callName;
    }

    public void setCallName(String callName) {
        this.callName = callName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

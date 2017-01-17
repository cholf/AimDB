package common;

/**
 * Created by bbking on 17-1-17.
 */
public enum TT {
    ONE_WAY(0,"单程"),
    GO_WAY(1,"去程"),
    BACK_WAY(2, "返程");

    private TT(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private int code;

    private String desc;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}

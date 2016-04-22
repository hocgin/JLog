package in.hocg.jlog.enums;

/**
 * Created by hocgin on 16-4-21.
 * 前景色
 */
public enum Foreground {
    Black("30"), Red("31"), Green("32"), Yellow("33"),
    Blue("34"), Purple("35"), Azure("36"), White("37"),
    None("");
    String code;

    Foreground(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

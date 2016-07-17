package in.hocg.jlog.enums;

/**
 * Created by hocgin on 16-4-21.
 * 背景色
 */
public enum Background {
    Black("40"), Red("41"), Green("42"), Yellow("43"),
    Blue("44"), Purple("45"), Azure("46"), White("47"),
    None("");
    String code;

    Background(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

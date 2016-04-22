package in.hocg.jlog.enums;

/**
 * Created by hocgin on 16-4-21.
 * 修饰
 */
public enum Modify {
    None("0"),
    Bold("1"),
    Underline("4"),;
    String code;

    Modify(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

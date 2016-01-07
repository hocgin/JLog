package io.github.hocgin.jlog.colors;

/**
 * Created by ubuntu on 16-1-7.
 */
public enum Colors {
    F_Black("字体黑色", "[30m"),
    F_Bed("字体黑色", "[31m"),
    F_Green("字体黑色", "[32m"),
    F_Yellow("字体黑色", "[33m"),
    F_Blue("字体黑色", "[34m"),
    F_Purple("字体黑色", "[35m"),
    F_DarkGreen("字体黑色", "[36m"),
    F_White("字体黑色", "[37m"),
    
    B_Black("背景黑色", "[40m"),
    B_Bed("背景黑色", "[41m"),
    B_Green("背景黑色", "[42m"),
    B_Yellow("背景黑色", "[43m"),
    B_Blue("背景黑色", "[44m"),
    B_Purple("背景黑色", "[45m"),
    B_DarkGreen("背景黑色", "[46m"),
    B_White("背景黑色", "[47m"),

    None("结尾", "[0m"),
    Border("加粗", "[1m")
    ;
    private String name;
    private String code;

    private Colors(String name, String code) {
        this.name = name;
        this.code = "\033" +code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

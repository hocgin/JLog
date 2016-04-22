package in.hocg.jlog.util;

import in.hocg.jlog.enums.Background;
import in.hocg.jlog.enums.Foreground;
import in.hocg.jlog.enums.Modify;

/**
 * Created by hocgin on 16-4-21.
 */
public class JRender {

    /**
     * 为文字增加 背景色/前景色
     *
     * @param str        文字
     * @param foreground 前景色
     * @param background 背景色
     * @return
     */
    public static String color(String str, Foreground foreground, Background background) {
        return String.format("\033[%s;%sm%s\033[%sm", LangKit.ifNull(background, Background.None).getCode(), LangKit.ifNull(foreground, Foreground.None).getCode(), str, Modify.None.getCode());
    }

    /**
     * 为文字增加 前景色
     *
     * @param str        文字
     * @param foreground 前景色
     * @return
     */
    public static String color(String str, Foreground foreground) {
        return color(str, foreground, Background.None);
    }

    /**
     * 为文字增加 修饰
     *
     * @param str    文字
     * @param modify 修饰
     * @return
     */
    public static String modify(String str, Modify... modify) {
        if (!LangKit.isNull(modify))
            for (Modify i : modify) {
                str = String.format("\033[%sm%s\033[%sm", i.getCode(), str, Modify.None.getCode());
            }
        return str;
    }

    /**
     * 为文字增加 背景色/前景色/修饰
     *
     * @param str        文字
     * @param foreground 前景色
     * @param background 背景色
     * @param modify     修饰
     * @return
     */
    public static String all(String str, Foreground foreground, Background background, Modify... modify) {
        return modify(color(str, foreground, background), modify);
    }

    /**
     * 为文字增加 前景色/修饰
     *
     * @param str        文字
     * @param foreground 前景色
     * @param modify     修饰
     * @return
     */
    public static String all(String str, Foreground foreground, Modify... modify) {
        return all(str, foreground, Background.None, modify);
    }
}

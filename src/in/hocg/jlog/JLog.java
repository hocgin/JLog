package in.hocg.jlog;

import in.hocg.jlog.core.JLogPrinter;
import in.hocg.jlog.core.Printer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;

/**
 * Created by hocgin on 16-4-21.
 * 日志打印
 */
public final class JLog {
    private static final String DEFAULT_TAG = "JLog";

    private static Printer printer = new JLogPrinter();

    private JLog() {
    }

    public static Settings init() {
        return init(DEFAULT_TAG);
    }

    public static Settings init(String tag) {
        printer = new JLogPrinter();
        return printer.init(tag);
    }

    public static void clear() {
        printer.clear();
        printer = null;
    }

    public static Printer tag(String tag) {
        return printer.tag(tag, printer.getSettings().getMethodCount());
    }

    public static Printer tag(int methodCount) {
        return printer.tag(null, methodCount);
    }

    public static Printer tag(String tag, int methodCount) {
        return printer.tag(tag, methodCount);
    }

    public static void d(String message, Object... args) {
        printer.d(message, args);
    }

    public static void e(String message, Object... args) {
        printer.e(null, message, args);
    }

    public static void e(Throwable throwable, String message, Object... args) {
        printer.e(throwable, message, args);
    }

    public static void i(String message, Object... args) {
        printer.i(message, args);
    }

    public static void v(String message, Object... args) {
        printer.v(message, args);
    }

    public static void w(String message, Object... args) {
        printer.w(message, args);
    }

    public static void wtf(String message, Object... args) {
        printer.wtf(message, args);
    }

    public static void json(String json) {
        printer.json(json);
    }
    public static void json(Object jsonObj) {
        printer.json(new JSONObject(jsonObj).toString());
    }
    public static void json(Object[] jsonObj) {
        printer.json(new JSONArray(jsonObj).toString());
    }
    public static void json(Collection jsonArr) {
        printer.json(new JSONArray(jsonArr).toString());
    }

    public static void xml(String xml) {
        printer.xml(xml);
    }

}

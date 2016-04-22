package in.hocg.jlog.core;

import in.hocg.jlog.JLog;
import in.hocg.jlog.Settings;
import in.hocg.jlog.enums.LogLevel;
import in.hocg.jlog.enums.LogType;
import in.hocg.jlog.util.LangKit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

import static in.hocg.jlog.enums.LogType.*;

/**
 * Created by hocgin on 16-4-21.
 */
public final class JLogPrinter implements Printer {

    private String tag;
    private Settings settings;

    private final ThreadLocal<String> localTag = new ThreadLocal<>();
    private final ThreadLocal<Integer> localMethodCount = new ThreadLocal<>();

    private static final int CHUNK_SIZE = 4000;

    private static final int MIN_STACK_OFFSET = 3;

    private static final int JSON_INDENT = 4;

    /**
     * Drawing toolbox
     */
    private static final char TOP_LEFT_CORNER = '╔';
    private static final char BOTTOM_LEFT_CORNER = '╚';
    private static final char MIDDLE_CORNER = '╟';
    private static final char HORIZONTAL_DOUBLE_LINE = '║';
    private static final String DOUBLE_DIVIDER = "════════════════════════════════════════════";
    private static final String SINGLE_DIVIDER = "────────────────────────────────────────────";
    private static final String TOP_BORDER = TOP_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String BOTTOM_BORDER = BOTTOM_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String MIDDLE_BORDER = MIDDLE_CORNER + SINGLE_DIVIDER + SINGLE_DIVIDER;

    @Override
    public Printer tag(String tag, int methodCount) {
        if (tag != null) {
            localTag.set(tag);
        }
        localMethodCount.set(methodCount);
        return this;
    }

    @Override
    public Settings init(String tag) {
        if (tag == null) {
            throw new NullPointerException("tag may not be null");
        }
        if (tag.trim().length() == 0) {
            throw new IllegalStateException("tag may not be empty");
        }
        this.tag = tag;
        this.settings = new Settings();
        return settings;
    }

    @Override
    public Settings getSettings() {
        return settings;
    }

    @Override
    public void d(String message, Object... args) {
        log(LogType.DEBUG, message, args);
    }

    @Override
    public void e(String message, Object... args) {
        log(ERROR, message, args);
    }

    @Override
    public void e(Throwable throwable, String message, Object... args) {
        if (throwable != null && message != null) {
            message += " : " + throwable.toString();
        }
        if (throwable != null && message == null) {
            message = throwable.toString();
        }
        if (message == null) {
            message = "No message/exception is set";
        }
        log(ERROR, message, args);
    }

    @Override
    public void w(String message, Object... args) {
        log(WARN, message, args);
    }

    @Override
    public void i(String message, Object... args) {
        log(INFO, message, args);
    }

    @Override
    public void v(String message, Object... args) {
        log(VERBOSE, message, args);
    }

    @Override
    public void wtf(String message, Object... args) {
        log(ASSERT, message, args);
    }

    @Override
    public void json(String json) {
        if (LangKit.isEmpty(json)) {
            d("Empty/Null org.json content");
            return;
        }
        try {
            json = json.trim();
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                String message = jsonObject.toString(JSON_INDENT);
                d(message);
                return;
            }
            if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                String message = jsonArray.toString(JSON_INDENT);
                d(message);
            }
        } catch (JSONException e) {
            e(e.getCause().getMessage() + "\n" + json);
        }
    }

    @Override
    public void xml(String xml) {
        if (LangKit.isEmpty(xml)) {
            d("Empty/Null xml content");
            return;
        }
        try {
            Source xmlInput = new StreamSource(new StringReader(xml));
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(xmlInput, xmlOutput);
            d(xmlOutput.getWriter().toString().replaceFirst(">", ">\n"));
        } catch (TransformerException e) {
            e(e.getCause().getMessage() + "\n" + xml);
        }
    }

    /**
     * 主要打印入口
     *
     * @param logType 类型
     * @param msg     message
     * @param args    格式化参数
     */
    private synchronized void log(LogType logType, String msg, Object... args) {
        if (settings.getLogLevel() == LogLevel.NONE) return;
        String tag = _getTag();
        String message = LangKit.format(msg, args);
        int methodCount = _getMethodCount();
        if (LangKit.isEmpty(message)) {
            message = "Empty/NULL log message";
        }
        _logTopBorder(logType, tag);
        _logHeaderContent(logType, tag, methodCount);
        //get bytes of message with system's default charset
        byte[] bytes = message.getBytes();
        int length = bytes.length;
        if (length <= CHUNK_SIZE) {
            if (methodCount > 0) {
                _logDivider(logType, tag);
            }
            _logContent(logType, tag, message);
            _logBottomBorder(logType, tag);
            return;
        }
        if (methodCount > 0) {
            _logDivider(logType, tag);
        }
        for (int i = 0; i < length; i += CHUNK_SIZE) {
            int count = Math.min(length - i, CHUNK_SIZE);
            //create a new String with system's default charset
            _logContent(logType, tag, new String(bytes, i, count));
        }
        _logBottomBorder(logType, tag);
    }

    /**
     * 打印顶部分割线
     * ╔════════════════════════════════════════════════════════════════════════════════════════
     *
     * @param logType
     * @param tag
     */
    private void _logTopBorder(LogType logType, String tag) {
        _out(logType, tag, TOP_BORDER);
    }

    /**
     * 输出文本内容
     * ║ dfaf
     *
     * @param logType
     * @param tag
     * @param message
     */
    private void _logContent(LogType logType, String tag, String message) {
        String[] lines = message.split(System.getProperty("line.separator"));
        for (String line : lines) {
            _out(logType, tag, HORIZONTAL_DOUBLE_LINE + " " + line);
        }
    }

    /**
     * 打印分割线
     * ╟────────────────────────────────────────────────────────────────────────────────────────
     *
     * @param logType
     * @param tag
     */
    private void _logDivider(LogType logType, String tag) {
        _out(logType, tag, MIDDLE_BORDER);
    }

    /**
     * 打印线程信息
     * ║ Thread: main
     * ╟────────────────────────────────────────────────────────────────────────────────────────
     * ║ Main.main  (Main.java:12)
     *
     * @param logType
     * @param tag
     * @param methodCount
     */
    private void _logHeaderContent(LogType logType, String tag, int methodCount) {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        if (settings.isShowThreadInfo()) {
            _out(logType, tag, HORIZONTAL_DOUBLE_LINE + " Thread: " + Thread.currentThread().getName());
            _logDivider(logType, tag);
        }
        String level = "";

        int stackOffset = _getStackOffset(trace) + settings.getMethodOffset();

        //corresponding method count with the current stack may exceeds the stack trace. Trims the count
        if (methodCount + stackOffset > trace.length) {
            methodCount = trace.length - stackOffset - 1;
        }

        for (int i = methodCount; i > 0; i--) {
            int stackIndex = i + stackOffset;
            if (stackIndex >= trace.length) {
                continue;
            }
            StringBuilder builder = new StringBuilder();
            builder.append("║ ")
                    .append(level)
                    .append(LangKit.getSimpleClassName(trace[stackIndex].getClassName()))
                    .append(".")
                    .append(trace[stackIndex].getMethodName())
                    .append(" ")
                    .append(" (")
                    .append(trace[stackIndex].getFileName())
                    .append(":")
                    .append(trace[stackIndex].getLineNumber())
                    .append(")");
            level += "   ";
            _out(logType, tag, builder.toString());
        }
    }

    /**
     * 打印底部分割线
     * ╚════════════════════════════════════════════════════════════════════════════════════════
     *
     * @param logType
     * @param tag
     */
    private void _logBottomBorder(LogType logType, String tag) {
        _out(logType, tag, BOTTOM_BORDER);
    }

    @Override
    public void clear() {
        settings = null;
    }

    private void _out(LogType logType, String tag, String message) {
        switch (logType) {
            case VERBOSE:
                settings.getLogTool().v(tag, message);
                break;
            case INFO:
                settings.getLogTool().i(tag, message);
                break;
            case WARN:
                settings.getLogTool().w(tag, message);
                break;
            case ERROR:
                settings.getLogTool().e(tag, message);
                break;
            case ASSERT:
                settings.getLogTool().wtf(tag, message);
                break;
            case DEBUG:
                // default is debug
            default:
                settings.getLogTool().d(tag, message);
                break;
        }
    }

    private int _getStackOffset(StackTraceElement[] trace) {
        for (int i = MIN_STACK_OFFSET; i < trace.length; i++) {
            StackTraceElement e = trace[i];
            String name = e.getClassName();
            if (!name.equals(JLogPrinter.class.getName()) && !name.equals(JLog.class.getName())) {
                return --i;
            }
        }
        return -1;
    }

    private int _getMethodCount() {
        Integer count = localMethodCount.get();
        int result = settings.getMethodCount();
        if (count != null) {
            localMethodCount.remove();
            result = count;
        }
        if (result < 0) {
            throw new IllegalStateException("methodCount cannot be negative");
        }
        return result;
    }

    private String _getTag() {
        String tag = localTag.get();
        if (tag != null) {
            localTag.remove();
            return tag;
        }
        return this.tag;
    }
}

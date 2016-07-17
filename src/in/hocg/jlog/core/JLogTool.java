package in.hocg.jlog.core;

import in.hocg.jlog.enums.Foreground;
import in.hocg.jlog.enums.LogType;
import in.hocg.jlog.enums.Modify;
import in.hocg.jlog.util.JRender;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hocgin on 16-4-21.
 */
public class JLogTool implements LogTool {

    private final String format = "%s %s %s";

    @Override
    public void d(String tag, String message) {
        System.out.println(_formatOut(tag, message, LogType.DEBUG));
    }

    @Override
    public void e(String tag, String message) {
        System.out.println(_formatOut(tag, message, LogType.ERROR));
    }

    @Override
    public void w(String tag, String message) {
        System.out.println(_formatOut(tag, message, LogType.WARN));
    }

    @Override
    public void i(String tag, String message) {
        System.out.println(_formatOut(tag, message, LogType.INFO));
    }

    @Override
    public void v(String tag, String message) {
        System.out.println(_formatOut(tag, message, LogType.VERBOSE));
    }

    @Override
    public void wtf(String tag, String message) {
        e(tag, message);
    }

    /**
     * 行输出格式
     *
     * @param tag
     * @param message
     * @param logType
     * @return
     */
    private String _formatOut(String tag, String message, LogType logType) {
        return String.format(this.format, _getCurrentTime(), _getTag(tag, logType), _getContent(message, logType));
    }

    /**
     * 当前时间格式
     *
     * @return
     */
    private String _getCurrentTime() {
        String dateFmt = "yyyy/MM/dd HH:mm:ss";
        return String.format("%s", new SimpleDateFormat(dateFmt).format(new Date()));
    }

    /**
     * 当前标签格式
     *
     * @param tag
     * @param logType
     * @return
     */
    private String _getTag(String tag, LogType logType) {
        String tagFmt = "%s/%s:";
        CharSequence icon = logType.name().subSequence(0, 1);
        String s = String.format(tagFmt, icon, tag);
        s = JRender.modify(s, Modify.Bold);
        return _renderColor(s, logType);
    }

    /**
     * 当前内容格式
     * @param message
     * @param logType
     * @return
     */
    private String _getContent(String message, LogType logType) {
        return _renderColor(message, logType);
    }

    /**
     * 渲染颜色
     *
     * @param str
     * @param logType
     * @return
     */
    private String _renderColor(String str, LogType logType) {
        switch (logType) {
            case VERBOSE:
                str = JRender.color(str, Foreground.Black);
                break;
            case INFO:
                str = JRender.color(str, Foreground.Green);
                break;
            case WARN:
                str = JRender.color(str, Foreground.Yellow);
                break;
            case ERROR:
                str = JRender.color(str, Foreground.Red);
                break;
            case ASSERT:
                str = JRender.color(str, Foreground.Azure);
                break;
            case DEBUG:
            default:
                str = JRender.color(str, Foreground.Blue);
        }
        return str;
    }
}

package in.hocg.jlog;

import in.hocg.jlog.core.JLogFormat;
import in.hocg.jlog.core.LogTool;
import in.hocg.jlog.enums.LogLevel;
import in.hocg.jlog.util.LangKit;

/**
 * Created by hocgin on 16-4-21.
 * 配置
 */
public final class Settings {
    private int methodCount = 2;
    private boolean showThreadInfo = true;
    private int methodOffset = 0;

    private LogTool logTool;
    private LogLevel logLevel = LogLevel.FULL;

    public int getMethodCount() {
        return methodCount;
    }

    public Settings setMethodCount(int methodCount) {
        if (methodCount < 0) {
            methodCount = 0;
        }
        this.methodCount = methodCount;
        return this;
    }

    public boolean isShowThreadInfo() {
        return showThreadInfo;
    }

    public Settings hideShowThreadInfo() {
        this.showThreadInfo = false;
        return this;
    }

    public int getMethodOffset() {
        return methodOffset;
    }

    public Settings setMethodOffset(int offset) {
        this.methodOffset = offset;
        return this;
    }

    public Settings setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
        return this;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public LogTool getLogTool() {
        if (LangKit.isNull(logTool)) {
            logTool = new JLogFormat();
        }
        return logTool;
    }

    public Settings setLogTool(LogTool logTool) {
        this.logTool = logTool;
        return this;
    }
}

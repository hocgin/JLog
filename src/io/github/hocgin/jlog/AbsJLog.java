package io.github.hocgin.jlog;

import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * Created by ubuntu on 16-1-7.
 */
public abstract class AbsJLog {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    boolean isDebug = true;

    public void setDebug(boolean isDebug) {
        this.isDebug = false;
    }

    protected abstract void e(String msg, Objects[] objects, CallBack callBack);
    protected abstract void v(String msg, Objects[] objects, CallBack callBack);
    protected abstract void w(String msg, Objects[] objects, CallBack callBack);

    interface CallBack {
        void run(String msg);
    }
}

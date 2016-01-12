package io.github.hocgin.jlog;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Created by moy on 16-1-11.
 */
public class JLog {

    public static boolean debug = true;
    private static CallBack mCallback = null;

    enum Level{
        V, // 普通级别
        W, // 警告级别
        E // 错误级别
    }

    protected void log(String msg, Objects[] objects, CallBack callBack) {
        if (debug) {
            _ns();
            _nn(msg, objects, callBack);
            _ne();
        }
    }

    private void _ns() {

    }

    private void _nn(String msg, Objects[] objects, CallBack callBack) {
        msg = String.format(msg, objects);
        System.out.println(msg);
        _autoCallBack(msg, callBack);
    }

    private void _nn(Object obj, CallBack callBack) {
        Class<?> aClass;
        String msg = "";
        try {
            aClass = ClassLoader.getSystemClassLoader().loadClass("com.google.gson.Gson");
            Method toJson = aClass.getDeclaredMethod("toJson", Object.class);
            msg = (String) toJson.invoke(aClass.newInstance(), obj);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        System.out.println(msg);
        if (callBack != null) {
            callBack.run(msg);
        } else if (mCallback != null) {
            mCallback.run(msg);
        }
    }

    private void _ne() {

    }

    public static void setCallback(CallBack callback) {
        JLog.mCallback = callback;
    }

    interface CallBack {
        void run(String msg);
    }

    /**
     * auto call print call back
     * @param msg
     * @param callBack
     */
    private void _autoCallBack(String msg, CallBack callBack) {
        if (callBack != null) {
            callBack.run(msg);
        } else if (mCallback != null) {
            mCallback.run(msg);
        }
    }


}

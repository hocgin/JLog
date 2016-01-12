package io.github.hocgin.jlog;

import io.github.hocgin.jlog.utils.Clazz;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by moy on 16-1-12.
 */
public class FormatSingle {

    private static FormatSingle ME;
    Clazz gson;
    Clazz gsonBuilder;

    private FormatSingle() {
        try {
            gson = Clazz.load("com.google.gson.Gson");
            Object obj = Clazz.load("com.google.gson.GsonBuilder").callDeclaredMethod("setPrettyPrinting", null, null);
            gsonBuilder = new Clazz(new Clazz(obj).callDeclaredMethod("create", null, null));
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static final FormatSingle NEW() {
        if (ME == null) {
            synchronized (FormatSingle.class) {
                if (ME == null) return ME = new FormatSingle();
            }
        }
        return ME;
    }


    /**
     * 转化成普通的JSON
     * @param object
     * @return
     */
    public String toJson(Object object) {
        try {
            return (String) gson.callDeclaredMethod("toJson", new Class[]{Object.class}, new Object[]{object});
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 转化成漂亮的JSON
     * @param object
     * @return
     */
    public String toPrettyJson(Object object) {
        try {
            return (String) gsonBuilder.callDeclaredMethod("toJson", new Class[]{Object.class}, new Object[]{object});
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return "";
    }

}

package io.github.hocgin.jlog.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by moy on 16-1-12.
 */
public class Clazz {

    private Class<?> targetClass;
    private Object targetObj;

    public Clazz(String clazzPack) {
        try {
            this.targetClass = ClassLoader.getSystemClassLoader().loadClass(clazzPack);
            this.targetObj = targetClass.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Clazz(Object obj) {
        this.targetClass = obj.getClass();
        this.targetObj = obj;
    }

    public Clazz(Class clazz) {
        this.targetClass = clazz;
        try {
            this.targetObj = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * call Declared Method
     * @param name
     * @param clazz
     * @param obj
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Object callDeclaredMethod(String name, Class<?>[] clazz, Object[] obj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (clazz != null && obj != null && clazz.length != obj.length) {
            throw new IllegalAccessException("The length is not consistent.!");
        }
        if (clazz == null) {
            Method method = targetClass.getDeclaredMethod(name);
            return method.invoke(targetObj);
        }
        Method method = targetClass.getDeclaredMethod(name, clazz);
        return method.invoke(targetObj, obj);
    }

    /**
     * call Method
     * @param name
     * @param clazz
     * @param obj
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Object callMethod(String name, Class<?>[] clazz, Object[] obj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (clazz.length != obj.length) {
            throw new IllegalAccessException("The length is not consistent.!");
        }
        if (clazz == null) {
            Method method = targetClass.getDeclaredMethod(name);
            return method.invoke(targetObj);
        }
        Method method = targetClass.getDeclaredMethod(name, clazz);
        return method.invoke(targetObj, obj);
    }

    public static Clazz load(String clazzPack) throws ClassNotFoundException {
        return new Clazz(clazzPack);
    }

}

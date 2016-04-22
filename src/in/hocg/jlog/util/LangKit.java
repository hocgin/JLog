package in.hocg.jlog.util;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hocgin on 16-4-21.
 */
public class LangKit {

    /**
     * 将数组拆解成字符串，并指定隔开的符号
     *
     * @param array
     * @param separated
     * @param <T>
     * @return
     */
    public static <T> String toString(T[] array, String separated) {
        if (null != array && array.length > 0) {
            StringBuffer sb = new StringBuffer();
            for (T t : array) {
                sb.append(t.toString());
                sb.append(separated);
            }
            return sb.substring(0, sb.length() - separated.length());
        }
        return "";
    }

    /**
     * 拼接
     *
     * @param array
     * @param a
     * @param <T>
     * @return
     */
    public static <T> T[] array(T[] array, T... a) {
        List<T> ts = Arrays.asList(array);
        ts.addAll(Arrays.asList(a));
        return (T[]) ts.toArray();
    }

    /**
     * 拼接
     *
     * @param array
     * @param a
     * @param <T>
     * @return
     */
    public static <T> List<T> list(T[] array, T... a) {
        List<T> ts = Arrays.asList(array);
        ts.addAll(Arrays.asList(a));
        return ts;
    }

    /**
     * 为空，返回默认值
     *
     * @param o
     * @param def
     * @param <T>
     * @return
     */
    public static <T> T ifNull(T o, T def) {
        if (isNull(o)) {
            return def;
        }
        return o;
    }

    public static <T> boolean isNull(T o) {
        return o == null || "".equals(o);
    }


    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static String format(String message, Object... args) {
        return args.length == 0 ? message : String.format(message, args);
    }

    public static String getSimpleClassName(String name) {
        int lastIndex = name.lastIndexOf(".");
        return name.substring(lastIndex + 1);
    }
}

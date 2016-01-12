import com.google.gson.Gson;
import io.github.hocgin.jlog.FormatSingle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by ubuntu on 16-1-7.
 *
 / JLog Class Method
$-------------------
 内存/Jdk/OS


 */
public class Main {
    public static void main(String args[]) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Gson gson = new Gson();
//        String s = gson.toJson(new TestBean("n", "val"));
//        System.out.println("-->" + Colors.B_Black.getCode() + Colors.F_Bed.getCode() + Colors.Border.getCode() + s + Colors.None.getCode());


        Class<?> aClass = ClassLoader.getSystemClassLoader().loadClass("com.google.gson.Gson");
        Method toJson = aClass.getDeclaredMethod("toJson", Object.class);
        Object ret = toJson.invoke(aClass.newInstance(), new TestBean("n", "val"));
        String s = FormatSingle.NEW().toJson(new TestBean("n", "val"));
        System.out.println("  # --> " + s);


    }
}

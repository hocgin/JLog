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
//        Gson gson = new Gson();
//        String s = gson.toJson(new TestBean("n", "val"));
//        System.out.println("-->" + Colors.B_Black.getCode() + Colors.F_Bed.getCode() + Colors.Border.getCode() + s + Colors.None.getCode());


        Class<?> aClass = ClassLoader.getSystemClassLoader().loadClass("com.google.gson.Gson");

        Method[] methods = aClass.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
//            System.out.println("  # --> " + methods[i].getName());
            Method toJson = aClass.getDeclaredMethod("toJson", Object.class);
            Object ret = toJson.invoke(aClass.newInstance(), new TestBean("n", "val"));
            System.out.println("  # --> " + ((String) ret));
        }
        System.out.println("  # --> " +aClass.getDeclaredMethods().length);
    }
}

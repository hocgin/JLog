import com.google.gson.Gson;
import io.github.hocgin.jlog.colors.Colors;

/**
 * Created by ubuntu on 16-1-7.
 */
public class Main {
    public static void main(String args[]) {
        Gson gson = new Gson();
        String s = gson.toJson(new TestBean("n", "val"));
        System.out.println("-->" + Colors.B_Black.getCode() + Colors.F_Bed.getCode() + Colors.Border.getCode() + s + Colors.None.getCode());
    }
}

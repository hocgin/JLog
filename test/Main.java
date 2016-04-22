import in.hocg.jlog.JLog;
import in.hocg.jlog.enums.Foreground;
import in.hocg.jlog.util.JRender;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hocgin on 16-4-21.
 */
public class Main {

    public static void main(String[] args) {
        JLog.init().setMethodCount(2);


        List<User> users = new ArrayList<>();
        User user;
        for (int i = 0; i < 2; ) {
            user = new User("No." + ++i);
            users.add(user);
        }
        User xiaoMing = new User(users, "xiaoMing");

        JLog.json(users);
        JLog.json(xiaoMing);
        JLog.d("d Hello World");
        JLog.v("v Hello World");
        JLog.e("e Hello World");
        JLog.i("i Hello World");
        JLog.w("w Hello World");
        JLog.wtf("wtf Hello World");
        JLog.e(new IndexOutOfBoundsException(), "e xnull");
        JLog.xml("<is>xml</is>");
        JLog.tag("this is tag").xml("<is>xml</is>");

        System.out.println(JRender.color("Azure &&&", Foreground.Azure));
        System.out.println(JRender.color("Purple &&&", Foreground.Purple));
        System.out.println(JRender.color("Blue &&&", Foreground.Blue));
        System.out.println(JRender.color("Black &&&", Foreground.Black));
        System.out.println(JRender.color("Green &&&", Foreground.Green));
        System.out.println(JRender.color("Red &&&", Foreground.Red));
        System.out.println(JRender.color("White &&&", Foreground.White));
        System.out.println(JRender.color("Yellow &&&", Foreground.Yellow));
    }


}

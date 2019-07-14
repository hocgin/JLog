package in.hocg;

import in.hocg.jlog.JLog;
import in.hocg.jlog.enums.Foreground;
import in.hocg.jlog.util.JRender;
import in.hocg.logger.Log4j;
import in.hocg.pojo.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hocgin on 2019/7/14.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class Log4jMain {
    static {
        JLog.init().setLogTool(new Log4j()).setMethodCount(2);
    }
    
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        User user;
        for (int i = 0; i < 2; ) {
            user = new User("No." + ++i);
            users.add(user);
        }
        User xiaoMing = new User(users, "xiaoMing");
        
        Object o = null;
        
        JLog.json(o);
        JLog.json(users);
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

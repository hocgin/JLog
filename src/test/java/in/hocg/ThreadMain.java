package in.hocg;

import in.hocg.jlog.JLog;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hocgin on 2019/7/14.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class ThreadMain {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        JLog.init();
        executorService.submit(() -> {
            while (true) {
                JLog.d("你好");
            }
        });
        executorService.submit(() -> {
            while (true) {
                JLog.d("你1好");
            }
        });
        executorService.submit(() -> {
            while (true) {
                JLog.d("你2好");
            }
        });
        executorService.submit(() -> {
            while (true) {
                JLog.d("你3好");
            }
        });
        
        Thread.sleep(10000L);
    }
}

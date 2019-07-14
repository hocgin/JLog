package in.hocg.logger;

import in.hocg.jlog.core.LogTool;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hocgin on 2019/7/14.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
public class Log4j implements LogTool {
    @Override
    public void d(String tag, String message) {
        log.error("{} - {}", tag, message);
    }
    
    @Override
    public void e(String tag, String message) {
        log.error("{} - {}", tag, message);
    }
    
    @Override
    public void w(String tag, String message) {
        log.warn("{} - {}", tag, message);
    }
    
    @Override
    public void i(String tag, String message) {
        log.info("{} - {}", tag, message);
    }
    
    @Override
    public void v(String tag, String message) {
        log.info("{} - {}", tag, message);
    }
    
    @Override
    public void wtf(String tag, String message) {
        log.info("{} - {}", tag, message);
    }
}

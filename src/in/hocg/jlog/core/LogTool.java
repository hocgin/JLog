package in.hocg.jlog.core;

/**
 * Created by hocgin on 16-4-21.
 */
public interface LogTool {
  void d(String tag, String message);

  void e(String tag, String message);

  void w(String tag, String message);

  void i(String tag, String message);

  void v(String tag, String message);

  void wtf(String tag, String message);
}
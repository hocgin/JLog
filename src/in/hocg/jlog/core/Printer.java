package in.hocg.jlog.core;


import in.hocg.jlog.Settings;

/**
 * Created by hocgin on 16-4-21.
 */
public interface Printer {

  Printer tag(String tag, int methodCount);

  Settings init(String tag);

  Settings getSettings();

  void d(String message, Object... args);

  void e(String message, Object... args);

  void e(Throwable throwable, String message, Object... args);

  void w(String message, Object... args);

  void i(String message, Object... args);

  void v(String message, Object... args);

  void wtf(String message, Object... args);

  void json(String json);

  void xml(String xml);

  void clear();
}

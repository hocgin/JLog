## JLog
> This is `Logger` for Java

## Update
> **1.0.1** 2016年07月17日11:31:30

- 修复 json(null) 异常
- 清除目录结构

## Download
[JLog - 1.0.0](http://7xs6lq.com1.z0.glb.clouddn.com/github/jarJLog.jar)

## Usage
### Settings (optional)
```java
        JLog
                .init(YOUR_TAG)                 // default JLog or use just init()
                .methodCount(3)                 // default 2
                .hideThreadInfo()               // default shown
                .logLevel(LogLevel.NONE)        // default LogLevel.FULL
                .methodOffset(2)                // default 0
                .logTool(new JLogTool());       // custom log tool, optional
```
### More log samples
```java
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
```
## Console Print
![image](http://7xs6lq.com1.z0.glb.clouddn.com/JLog_2.png)
![image](http://7xs6lq.com1.z0.glb.clouddn.com/JLog_1.png)


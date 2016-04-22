## JLog
> This is `Logger` for Java

## Usage
```java
JLog.init();
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
![image](http://7xs6lq.com1.z0.glb.clouddn.com/JLog_1.png)
![image](http://7xs6lq.com1.z0.glb.clouddn.com/JLog_2.png)


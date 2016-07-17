import java.util.List;

/**
 * Created by hocgin on 16-4-21.
 */
public class User {
    private String name;
    private List<User> users;

    public User(String name) {
        this.name = name;
    }

    public User(List<User> users, String name) {
        this.users = users;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

package tvcompany.salemanager.model;

import java.util.List;

/**
 * Created by vietp on 04/09/2016.
 */
public class Status2 {
    private int code;
    private List<User1> user;

    public Status2() {
    }

    public Status2(int code, List<User1> user) {
        this.code = code;
        this.user = user;
    }

    public List<User1> getUser() {
        return user;
    }

    public void setUser(List<User1> user) {
        this.user = user;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

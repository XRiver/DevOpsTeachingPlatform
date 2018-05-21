package teamworkbranch.module.user.vo;

import java.io.Serializable;

/**
 * Created by liying on 2017/4/24.
 */
public class LoginInfo implements Serializable {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
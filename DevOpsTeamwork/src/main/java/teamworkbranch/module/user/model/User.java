package teamworkbranch.module.user.model;

import java.sql.Timestamp;

/**
 * Created by caosh on 2018/4/1.
 */
public class User {
    private String username;//用户名
    private String password;
    private String name;//姓名
    private String userId;//学号/工号
    private String email;
    private int role;//学生or教师
    private Timestamp createTime;//创建时间
    private Timestamp updateTime;//更新时间

    public User(String username, String password, String name, String userId
            , String email, int role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.userId = userId;
        this.email = email;
        this.role = role;

    }

    public User(){

    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}

package teamworkbranch.module.user.vo;


import java.sql.Timestamp;

/**
 * Created by caosh on 2018/4/1.
 */
public class UserVO {
    private String username;//用户名
    private String name;//姓名
    private String userId;//学号/工号
    private String email;
    private int role;//学生or教师
    private Timestamp createTime;//创建时间
    private Timestamp updateTime;//更新时间

    public UserVO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setCreateTime(Timestamp create_time) {
        this.createTime = create_time;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp update_time) {
        this.updateTime = update_time;
    }
}
package module.entity.VO;

import module.user.model.Role;

import java.util.Date;

/**
 * Created by caosh on 2018/4/1.
 */
public class UserVO {
    private String username;//用户名
    private String password;
    private String name;//姓名
    private String userId;//学号/工号
    private String email;
    private Role role;//学生or教师
    private Date create_time;//创建时间
    private Date update_time;//更新时间

    public UserVO(String username, String password, String name, String userId
            , String email, Role role, Date create_time, Date update_time) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.userId = userId;
        this.email = email;
        this.role = role;
        this.create_time = create_time;
        this.update_time = update_time;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}

package module.user.vo;

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

}

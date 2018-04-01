package entity;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by caosh on 2018/4/1.
 */
public class UserVO {
    private String username;//用户名
    private String password;
    private String name;//姓名
    private String number;//学号
    private String e_mail;
    private int user_id;//用户编号
    private Role role;//学生or教师
    private Date create_time;//创建时间
    private Date update_time;//更新时间
    private ArrayList<GroupVO> groupVOs;//所属团队，一对多

}

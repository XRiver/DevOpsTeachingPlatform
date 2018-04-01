package entity;

import java.util.Date;

/**
 * Created by caosh on 2018/4/1.
 */
public class ProjectVO {
    private String project_name;
    private String info;//项目信息
    private int group_id;//对应团队编号
    private UserVO creator;//项目创建者
    private UserVO manager;//项目管理者
    private Date create_time;//创建时间
    private Date update_time;//更新时间
}

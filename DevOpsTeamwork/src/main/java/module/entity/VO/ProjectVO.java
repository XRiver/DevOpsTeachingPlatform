package module.entity.VO;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by caosh on 2018/4/1.
 */
public class ProjectVO {
    private String project_name;
    private String info;//项目信息
    private int project_id;//项目编号
    private int group_id;//对应团队编号
    private UserVO creator;//项目创建者
    private ArrayList<UserVO> managerList;//项目管理者
    private Date create_time;//创建时间
    private Date update_time;//更新时间

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public ArrayList<UserVO> getManagerList() {
        return managerList;
    }

    public void setManagerList(ArrayList<UserVO> managerList) {
        this.managerList = managerList;
    }

    public UserVO getCreator() {
        return creator;
    }

    public void setCreator(UserVO creator) {
        this.creator = creator;
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

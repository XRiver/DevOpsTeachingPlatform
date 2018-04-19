package teamworkbranch.module.project.model;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by liying on 2018/4/13.
 */
public class Project {
    private int projectId;//项目编号数据库自增
    private String creatorName;//创建者用户名
    private String name;//项目名
    private String info;//项目介绍
    private int groupId;//项目负责组号
    private Timestamp createTime;//创建日期
    private Timestamp updateTime;//最后修改日期
    private ArrayList<String> managerList;//管理者用户名

    public Project(){

    }

    public Project(String creatorName, String name, String info, int groupId) {
        this.creatorName = creatorName;
        this.name = name;
        this.info = info;
        this.groupId = groupId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }


}

package teamworkbranch.module.project.model;

import java.sql.Timestamp;
import java.util.List;

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
    private List<PManager> managerList;//管理者用户名
    private String tool;//持续集成使用的工具

    public Project(){

    }

    public Project(String creatorName, String name, String info, int groupId,String tool) {
        this.creatorName = creatorName;
        this.name = name;
        this.info = info;
        this.groupId = groupId;
        this.tool=tool;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
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

    public List<PManager> getManagerList() {
        return managerList;
    }

    public void setManagerList(List<PManager> managerList) {
        this.managerList = managerList;
    }
}

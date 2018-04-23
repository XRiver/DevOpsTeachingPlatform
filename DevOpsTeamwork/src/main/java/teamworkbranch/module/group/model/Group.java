package teamworkbranch.module.group.model;

import java.sql.Timestamp;

/**
 * Created by caosh on 2018/4/19.
 */
public class Group {
    private int groupId;//团队编号
    private String creator_Name;//创建者
    private String name;//团队名称
    private String info;//团队介绍
    private Timestamp createTime;//创建日期
    private Timestamp updateTime;//最后修改日期

    public Group() {
    }

    public Group(String name, String info, String creator_Name) {
        this.name = name;
        this.info = info;
        this.creator_Name = creator_Name;
    }

    public int getgroupId() {
        return groupId;
    }

    public void setgroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getcreator_Name() {
        return creator_Name;
    }

    public void setcreator_Name(String creator_Name) {
        this.creator_Name = creator_Name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

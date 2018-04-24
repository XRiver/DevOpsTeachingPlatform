package teamworkbranch.module.group.model;

import java.sql.Timestamp;

/**
 * Created by caosh on 2018/4/19.
 */
public class GMember {
    private int groupId;//团队编号
    private String member_Name;//成员名称
    private int is_manager;
    private Timestamp createTime;//创建日期
    private Timestamp updateTime;//最后修改日期

    public GMember() {
    }

    public GMember(int groupId, String member_Name,int is_manager) {
        this.groupId = groupId;
        this.member_Name = member_Name;
        this.is_manager = is_manager;
    }

    public int getgroupId() {
        return groupId;
    }

    public void setgroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getmember_Name() {
        return member_Name;
    }

    public void setmember_Name(String member_Name) {
        this.member_Name = member_Name;
    }

    public int getIs_manager() {
        return is_manager;
    }

    public void setIs_manager(int is_manager) {
        this.is_manager = is_manager;
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

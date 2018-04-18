package module.group.model;

import java.sql.Timestamp;

/**
 * Created by caosh on 2018/4/18.
 */
public class GMember {
    private int groupId;
    private int isManager;
    private String memberName;
    private Timestamp createTime;//创建日期
    private Timestamp updateTime;//最后修改日期

    public GMember(int groupId, String memberName) {
        this.groupId = groupId;
        this.memberName = memberName;
    }

    public GMember() {
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getIsManager() {
        return isManager;
    }

    public void setIsManager(int isManager) {
        this.isManager = isManager;
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

package teamworkbranch.module.group.model;

import java.sql.Timestamp;

/**
 * Created by caosh on 2018/4/19.
 */
public class GMember {
    private int group_id;//团队编号
    private String member_name;//成员名称
    private int is_manager;
    private Timestamp createTime;//创建日期
    private Timestamp updateTime;//最后修改日期

    public GMember() {
    }

    public GMember(int group_id, String member_name) {
        this.group_id = group_id;
        this.member_name = member_name;
        this.is_manager = 0;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
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

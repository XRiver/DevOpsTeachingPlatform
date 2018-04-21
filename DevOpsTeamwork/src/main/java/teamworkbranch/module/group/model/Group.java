package teamworkbranch.module.group.model;

import java.sql.Timestamp;

/**
 * Created by caosh on 2018/4/19.
 */
public class Group {
    private int group_id;//团队编号
    private String creator_name;//创建者
    private String name;//团队名称
    private String info;//团队介绍
    private Timestamp createTime;//创建日期
    private Timestamp updateTime;//最后修改日期

    public Group() {
    }

    public Group(String name, String info, String creator_name) {
        this.name = name;
        this.info = info;
        this.creator_name = creator_name;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
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

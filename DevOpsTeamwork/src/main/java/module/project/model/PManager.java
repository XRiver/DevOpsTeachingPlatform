package module.project.model;

import java.sql.Timestamp;

/**
 * Created by liying on 2018/4/16.
 */
public class PManager {
    private int projectId;
    private String managerName;
    private Timestamp createTime;//创建日期
    private Timestamp updateTime;//最后修改日期

    public PManager(int projectId, String managerName) {
        this.projectId = projectId;
        this.managerName = managerName;
    }

    public PManager() {
    }

    public String getManagerName() {
        return managerName;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
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

package teamworkbranch.module.log.model;

import java.sql.Timestamp;

/**
 * Created by liying on 2018/4/25.
 */
public class Log {
    private Timestamp createTime;//创建时间
    private Timestamp updateTime;//更新时间
    private String info;//日志内容
    private String username;//执行操作的用户
    private int logId;//日志id 数据库自增
    private String projectId;//项目id

    public Log(String info, String username, String projectId) {
        this.info = info;
        this.username = username;
        this.projectId = projectId;
    }

    public Log() {
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}

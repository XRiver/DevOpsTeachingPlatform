package Devops.docker.DockerBranch.VO;

public class deployInfoVO {
    private String taskId;//任务id
    private String date;//日期
    private String lastDate;//上一次日期
    private String creator;//创建者
    private String description;//描述
    private String software;//软件包
    private String path;//部署路径
    private hostVO host;//主机

    public deployInfoVO(String taskId, String date, String lastDate, String creator, String description, String software, String path, hostVO host) {
        this.taskId = taskId;
        this.date = date;
        this.lastDate = lastDate;
        this.creator = creator;
        this.description = description;
        this.software = software;
        this.path = path;
        this.host = host;
    }

    public deployInfoVO() {
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public hostVO getHost() {
        return host;
    }

    public void setHost(hostVO host) {
        this.host = host;
    }
}

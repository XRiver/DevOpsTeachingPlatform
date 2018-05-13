package Devops.docker.DockerBranch.VO;

public class taskVO {
    private String taskId;//任务id
    private String taskName;//任务名
    private String lastDate;//上一次部署日期
    private String status;//部署成功或部署失败
    private String creator;//创建者姓名
    private String branchname;//对应分支名

    public taskVO(String taskId, String taskName, String lastDate, String status, String creator, String branchname) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.lastDate = lastDate;
        this.status = status;
        this.creator = creator;
        this.branchname = branchname;
    }

    public taskVO() {
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}

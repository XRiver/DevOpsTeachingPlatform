package Devops.docker.DockerBranch.VO;

public class taskVO {
    private String taskId;
    private String taskName;
    private String time;
    private String status;
    private String creator;

    public taskVO(String taskId, String taskName, String time, String status, String creator) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.time = time;
        this.status = status;
        this.creator = creator;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

package Devops.docker.DockerBranch.VO;

public class deployStatusVO {
    private String taskId;
    private int status;//部署状态，包含1:连接到部署引擎，2：准备部署（生成文档）3：正在部署（开始运行）4：部署完成 0：部署失败
    private String log;//部署日志

    public deployStatusVO(String taskId, int status, String log) {
        this.taskId = taskId;
        this.status = status;
        this.log = log;
    }

    public deployStatusVO() {
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}

package Devops.docker.DockerBranch.VO;

public class deployHistoryVO {
    private String historyId;//历史id
    private String operatorName;//创建者姓名
    private String date;//日期
    private String status;//部署结果
    private String time;//部署时间

    public deployHistoryVO(String historyId, String operatorName, String date, String status, String time) {
        this.historyId = historyId;
        this.operatorName = operatorName;
        this.date = date;
        this.status = status;
        this.time = time;
    }

    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

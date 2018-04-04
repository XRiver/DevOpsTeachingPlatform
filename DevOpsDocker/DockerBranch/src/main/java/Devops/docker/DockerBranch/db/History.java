package Devops.docker.DockerBranch.db;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class History {
    private int historyId;
    private String operator;
    private String date;
    private String status;
    private String time;
    private Integer taskId;

    @Id
    @Column(name = "historyId")
    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    @Basic
    @Column(name = "operator")
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Basic
    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Basic
    @Column(name = "taskId")
    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return historyId == history.historyId &&
                Objects.equals(operator, history.operator) &&
                Objects.equals(date, history.date) &&
                Objects.equals(status, history.status) &&
                Objects.equals(time, history.time) &&
                Objects.equals(taskId, history.taskId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(historyId, operator, date, status, time, taskId);
    }
}
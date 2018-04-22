package Devops.docker.DockerBranch.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Task {

    private int taskId;
    private String name;
    private String description;
    private Integer hostId;
    private String projectId;
    private String creator;
    private String lastDate;
    private String status;
    private String software;

    @Id
    @Column(name = "task_Id", nullable = false)
    @GeneratedValue(strategy=GenerationType.TABLE,generator="tableGenerator")
    @TableGenerator(name="tableGenerator",initialValue=0,allocationSize=1)
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 300)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "host_Id", nullable = true)
    public Integer getHostId() {
        return hostId;
    }

    public void setHostId(Integer hostId) {
        this.hostId = hostId;
    }

    @Basic
    @Column(name = "project_Id", nullable = true, length = 20)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "creator", nullable = true, length = 20)
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "last_Date", nullable = true, length = 20)
    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 20)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "software", nullable = true, length = 30)
    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return taskId == task.taskId &&
                Objects.equals(name, task.name) &&
                Objects.equals(description, task.description) &&
                Objects.equals(hostId, task.hostId) &&
                Objects.equals(projectId, task.projectId) &&
                Objects.equals(creator, task.creator) &&
                Objects.equals(lastDate, task.lastDate) &&
                Objects.equals(status, task.status) &&
                Objects.equals(software, task.software);
    }

    @Override
    public int hashCode() {

        return Objects.hash(taskId, name, description, hostId, projectId, creator, lastDate, status, software);
    }
}
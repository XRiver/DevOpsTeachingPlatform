package Devops.docker.DockerBranch.db;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Container {
    private int containerId;
    private String creator;
    private String image;
    private String path;
    private String port;
    private String date;
    private Integer taskId;

    @Id
    @Column(name = "containerId")
    public int getContainerId() {
        return containerId;
    }

    public void setContainerId(int containerId) {
        this.containerId = containerId;
    }

    @Basic
    @Column(name = "creator")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "port")
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
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
        Container container = (Container) o;
        return containerId == container.containerId &&
                Objects.equals(creator, container.creator) &&
                Objects.equals(image, container.image) &&
                Objects.equals(path, container.path) &&
                Objects.equals(port, container.port) &&
                Objects.equals(date, container.date) &&
                Objects.equals(taskId, container.taskId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(containerId, creator, image, path, port, date, taskId);
    }
}

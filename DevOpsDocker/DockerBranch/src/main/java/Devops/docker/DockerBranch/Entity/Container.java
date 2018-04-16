package Devops.docker.DockerBranch.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Container {
    private int containerId;
    private String containerName;
    private String creator;
    private String image;
    private String path;
    private String port;
    private String date;
    private Integer taskId;

    @Id
    @Column(name = "container_Id", nullable = false)
    @GeneratedValue(strategy=GenerationType.TABLE,generator="tableGenerator")
    @TableGenerator(name="tableGenerator",initialValue=0,allocationSize=1)
    public int getContainerId() {
        return containerId;
    }

    public void setContainerId(int containerId) {
        this.containerId = containerId;
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
    @Column(name = "image", nullable = true, length = 20)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "path", nullable = true, length = 30)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "port", nullable = true, length = 20)
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Basic
    @Column(name = "date", nullable = true, length = 20)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "task_Id", nullable = true)
    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    @Basic
    @Column(name = "container_name",nullable = true)
    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Container container = (Container) o;
        return containerId == container.containerId &&
                Objects.equals(containerName, container.containerName) &&
                Objects.equals(creator, container.creator) &&
                Objects.equals(image, container.image) &&
                Objects.equals(path, container.path) &&
                Objects.equals(port, container.port) &&
                Objects.equals(date, container.date) &&
                Objects.equals(taskId, container.taskId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(containerId, containerName, creator, image, path, port, date, taskId);
    }
}

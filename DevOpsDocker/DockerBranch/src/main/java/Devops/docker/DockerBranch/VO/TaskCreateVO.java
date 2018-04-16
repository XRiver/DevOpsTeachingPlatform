package Devops.docker.DockerBranch.VO;

import Devops.docker.DockerBranch.Entity.Container;

import java.util.List;

public class TaskCreateVO {
    private String taskId;
    private String name;
    private String description;
    private String hostId;
    private String projectId;
    private String username;
    private List<containerVO> containers;

    public TaskCreateVO(String taskId, String name, String description, String hostId, String projectId, String username, List<containerVO> containers) {
        this.taskId = taskId;
        this.name = name;
        this.description = description;
        this.hostId = hostId;
        this.projectId = projectId;
        this.username = username;
        this.containers = containers;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<containerVO> getContainers() {
        return containers;
    }

    public void setContainers(List<containerVO> containers) {
        this.containers = containers;
    }
}

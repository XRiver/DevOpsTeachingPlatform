package Devops.docker.DockerBranch.VO;


import java.util.List;

public class taskSpecificVO {
    private String taskId;
    private String name;
    private String description;
    private String hostId;
    private String projectId;
    private String userId;
    private List<imageVO> images;
    private List<containerVO> containers;

    public taskSpecificVO(String taskId, String name, String description, String hostId, String projectId, String userId, List<imageVO> images, List<containerVO> containers) {
        this.taskId = taskId;
        this.name = name;
        this.description = description;
        this.hostId = hostId;
        this.projectId = projectId;
        this.userId = userId;
        this.images = images;
        this.containers = containers;
    }

    public taskSpecificVO() {
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<imageVO> getImages() {
        return images;
    }

    public void setImages(List<imageVO> images) {
        this.images = images;
    }

    public List<containerVO> getContainers() {
        return containers;
    }

    public void setContainers(List<containerVO> containers) {
        this.containers = containers;
    }
}

package Devops.docker.DockerBranch.VO;


import java.util.List;

public class taskSpecificVO {
    private String taskId;
    private String name;
    private String description;
    private String hostIp;
    private String projectId;
    private String userName;
    private List<imageVO> images;
    private List<containerVO> containers;

    public taskSpecificVO(String taskId, String name, String description, String hostIp, String projectId, String username, List<imageVO> images, List<containerVO> containers) {
        this.taskId = taskId;
        this.name = name;
        this.description = description;
        this.hostIp = hostIp;
        this.projectId = projectId;
        this.userName = username;
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

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

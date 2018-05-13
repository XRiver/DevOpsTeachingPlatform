package Devops.docker.DockerBranch.VO;


import java.util.List;

public class taskSpecificVO {
    private String taskId;
    private String name;
    private String description;
    private String hostId;
    private String hostIp;
    private String projectId;
    private String userName;
    private int linkmethod;
    private String branchname;
    private List<containerVO> containers;

    public taskSpecificVO(String taskId, String name, String description, String hostId, String hostIp, String projectId, String userName, int linkmethod, String branchname, List<containerVO> containers) {
        this.taskId = taskId;
        this.name = name;
        this.description = description;
        this.hostId = hostId;
        this.hostIp = hostIp;
        this.projectId = projectId;
        this.userName = userName;
        this.linkmethod = linkmethod;
        this.branchname = branchname;
        this.containers = containers;
    }

    public taskSpecificVO() {
    }

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public int getLinkmethod() {
        return linkmethod;
    }

    public void setLinkmethod(int linkmethod) {
        this.linkmethod = linkmethod;
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

    public List<containerVO> getContainers() {
        return containers;
    }

    public void setContainers(List<containerVO> containers) {
        this.containers = containers;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }
}

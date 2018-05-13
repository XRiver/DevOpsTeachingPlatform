package Devops.docker.DockerBranch.VO;

import Devops.docker.DockerBranch.Entity.Container;

import java.util.List;

public class TaskCreateVO {
    private String taskId;
    private String name;
    private String description;
    private String hostId;
    private String projectId;
    private String groupname;
    private String projectname;
    private String username;
    private int linkmethod;
    private String branchname;
    private List<containerVO> containers;

    public TaskCreateVO(String taskId, String name, String description, String hostId, String projectId, String groupname, String projectname, String username, int linkmethod, String branchname, List<containerVO> containers) {
        this.taskId = taskId;
        this.name = name;
        this.description = description;
        this.hostId = hostId;
        this.projectId = projectId;
        this.groupname = groupname;
        this.projectname = projectname;
        this.username = username;
        this.linkmethod = linkmethod;
        this.branchname = branchname;
        this.containers = containers;
    }

    public TaskCreateVO() {
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

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
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

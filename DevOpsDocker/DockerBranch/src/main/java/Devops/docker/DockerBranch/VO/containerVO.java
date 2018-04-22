package Devops.docker.DockerBranch.VO;

import java.util.List;

public class containerVO {

    private String containerId;//容器id
    private String containerName;//容器名
    private String creator;//创建者
    private String image;//基于的镜像名
    private String path;//部署组件所在路径
    private String port;//访问端口设定
    private String filename;//包含的文件名
    private String date;//创建日期
    private List<String> containerList;//该容器连接（可访问）的容器名

    public containerVO(String containerId, String containerName, String creator, String image, String path, String port, String filename, String date, List<String> containerList) {
        this.containerId = containerId;
        this.containerName = containerName;
        this.creator = creator;
        this.image = image;
        this.path = path;
        this.port = port;
        this.filename = filename;
        this.date = date;
        this.containerList = containerList;
    }

    public containerVO() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getContainerList() {
        return containerList;
    }

    public void setContainerList(List<String> containerList) {
        this.containerList = containerList;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
package Devops.docker.DockerBranch.VO;

import java.util.List;

public class containerVO {

    private String containerId;//容器id
    private String creator;//创建者
    private String image;//基于的镜像名
    private String date;//创建日期
    private List<String> containerList;//该容器连接（可访问）的容器id

    public containerVO(String containerId, String creator, String image, String time, List<String> containerList) {
        this.containerId = containerId;
        this.creator = creator;
        this.image = image;
        this.date = time;
        this.containerList = containerList;
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
}

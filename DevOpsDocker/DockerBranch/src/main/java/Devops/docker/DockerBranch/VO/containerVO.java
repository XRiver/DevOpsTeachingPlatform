package Devops.docker.DockerBranch.VO;

import java.util.List;

public class containerVO {

    private String containerId;//容器id
    private String creator;
    private String image;
    private String time;
    private List<String> containerList;//该容器连接（可访问）的容器id

    public containerVO(String containerId, String creator, String image, String time, List<String> containerList) {
        this.containerId = containerId;
        this.creator = creator;
        this.image = image;
        this.time = time;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getContainerList() {
        return containerList;
    }

    public void setContainerList(List<String> containerList) {
        this.containerList = containerList;
    }
}

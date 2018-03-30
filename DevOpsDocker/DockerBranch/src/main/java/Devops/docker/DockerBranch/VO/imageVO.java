package Devops.docker.DockerBranch.VO;

/**
 *
 * 用户创建的镜像vo
 * path 镜像路径
 */
public class imageVO {

    private String imageId;
    private String name;
    private String path;
    private String basicImageName;
    private String shellScript;

    public imageVO(String imageId, String name, String path, String basicImageName, String shellScript) {
        this.imageId = imageId;
        this.name = name;
        this.path = path;
        this.basicImageName = basicImageName;
        this.shellScript = shellScript;

    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getBasicImageName() {
        return basicImageName;
    }

    public void setBasicImageName(String basicImageName) {
        this.basicImageName = basicImageName;
    }

    public String getShellScript() {
        return shellScript;
    }

    public void setShellScript(String shellScript) {
        this.shellScript = shellScript;
    }
}

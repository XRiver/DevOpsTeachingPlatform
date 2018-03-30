package Devops.docker.DockerBranch.VO;

/**
 * 基础镜像
 */

public class basicImageVO {

    private int basicid;
    private int name;

    public basicImageVO(int basicid, int name) {
        this.basicid = basicid;
        this.name = name;
    }

    public int getBasicid() {
        return basicid;
    }

    public void setBasicid(int basicid) {
        this.basicid = basicid;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}

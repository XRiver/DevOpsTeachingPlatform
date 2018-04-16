package Devops.docker.DockerBranch.VO;

/**
 * 基础镜像
 */

public class basicImageVO {

    private int basicid;//基础镜像id
    private String name;//基础镜像名
    private String shellScript;//对应默认脚本

    public basicImageVO(int basicid, String name, String shellScript) {
        this.basicid = basicid;
        this.name = name;
        this.shellScript = shellScript;
    }

    public basicImageVO() {
    }

    public int getBasicid() {
        return basicid;
    }

    public void setBasicid(int basicid) {
        this.basicid = basicid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShellScript() {
        return shellScript;
    }

    public void setShellScript(String shellScript) {
        this.shellScript = shellScript;
    }
}

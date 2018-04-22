package Devops.docker.DockerBranch.VO;


public class hostVO {
    private String hostId;//主机id
    private String hostname;//主机名
    private String opsSystem;//操作系统版本
    private String ip;//主机ip
    private String sshPort;//ssh端口
    private String creator;//创建者姓名
    private String auto_installed;//已经自动安装
    private String date;//创建日期

    public hostVO(String hostId, String hostname, String opsSystem, String ip, String sshPort, String creator, String auto_installed, String date) {
        this.hostId = hostId;
        this.hostname = hostname;
        this.opsSystem = opsSystem;
        this.ip = ip;
        this.sshPort = sshPort;
        this.creator = creator;
        this.auto_installed = auto_installed;
        this.date = date;
    }

    public hostVO() {
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getOpsSystem() {
        return opsSystem;
    }

    public void setOpsSystem(String opsSystem) {
        this.opsSystem = opsSystem;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuto_installed() {
        return auto_installed;
    }

    public void setAuto_installed(String auto_installed) {
        this.auto_installed = auto_installed;
    }

    public String getSshPort() {
        return sshPort;
    }

    public void setSshPort(String sshPort) {
        this.sshPort = sshPort;
    }
}

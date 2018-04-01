package Devops.docker.DockerBranch.VO;


public class hostVO {
    private String hostId;//主机id
    private String hostname;//主机名
    private String opsSystem;//操作系统版本
    private String ip;//主机ip
    private String creator;//创建者姓名
    private String date;//创建日期

    public hostVO(String hostId, String hostname, String opsSystem, String ip, String creator, String date) {
        this.hostId = hostId;
        this.hostname = hostname;
        this.opsSystem = opsSystem;
        this.ip = ip;
        this.creator = creator;
        this.date = date;
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
}

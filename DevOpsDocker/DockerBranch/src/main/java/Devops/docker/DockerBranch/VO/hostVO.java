package Devops.docker.DockerBranch.VO;


public class hostVO {
    private String hostId;
    private String hostname;
    private String opsSystem;
    private String ip;
    private String creator;
    private String time;

    public hostVO(String hostId, String hostname, String opsSystem, String ip, String creator, String time) {
        this.hostId = hostId;
        this.hostname = hostname;
        this.opsSystem = opsSystem;
        this.ip = ip;
        this.creator = creator;
        this.time = time;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

package Devops.docker.DockerBranch.db;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Host {
    private int hostId;
    private String hostname;
    private String ip;
    private String root;
    private String password;
    private String creator;
    private String projectid;
    private String createdDate;
    private String opsSystem;

    @Id
    @Column(name = "hostId")
    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    @Basic
    @Column(name = "hostname")
    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Basic
    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "root")
    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "creator")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "projectid")
    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    @Basic
    @Column(name = "createdDate")
    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "opsSystem")
    public String getOpsSystem() {
        return opsSystem;
    }

    public void setOpsSystem(String opsSystem) {
        this.opsSystem = opsSystem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Host host = (Host) o;
        return hostId == host.hostId &&
                Objects.equals(hostname, host.hostname) &&
                Objects.equals(ip, host.ip) &&
                Objects.equals(root, host.root) &&
                Objects.equals(password, host.password) &&
                Objects.equals(creator, host.creator) &&
                Objects.equals(projectid, host.projectid) &&
                Objects.equals(createdDate, host.createdDate) &&
                Objects.equals(opsSystem, host.opsSystem);
    }

    @Override
    public int hashCode() {

        return Objects.hash(hostId, hostname, ip, root, password, creator, projectid, createdDate, opsSystem);
    }
}

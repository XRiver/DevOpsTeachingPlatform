package Devops.docker.DockerBranch.Entity;

import javax.persistence.*;
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
    private String date;
    private String opsSystem;

    @Id
    @Column(name = "host_Id", nullable = false)
    @GeneratedValue(strategy=GenerationType.TABLE,generator="tableGenerator")
    @TableGenerator(name="tableGenerator",initialValue=0,allocationSize=1)
    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    @Basic
    @Column(name = "hostname", nullable = true, length = 30)
    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Basic
    @Column(name = "ip", nullable = true, length = 20)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "root", nullable = true, length = 20)
    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "creator", nullable = true, length = 20)
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "projectid", nullable = true, length = 20)
    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    @Basic
    @Column(name = "created_Date", nullable = true, length = 20)
    public String getDate() {
        return date;
    }

    public void setDate(String createdDate) {
        this.date = createdDate;
    }

    @Basic
    @Column(name = "ops_System", nullable = true, length = 20)
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
                Objects.equals(date, host.date) &&
                Objects.equals(opsSystem, host.opsSystem);
    }

    @Override
    public int hashCode() {

        return Objects.hash(hostId, hostname, ip, root, password, creator, projectid, date, opsSystem);
    }
}

package Devops.docker.DockerBranch.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Containerlink {
    private int linkid;
    private Integer masterid;
    private String linkedname;

    @Id
    @Column(name = "linkid", nullable = false)
    @GeneratedValue(strategy= GenerationType.TABLE,generator="tableGenerator")
    @TableGenerator(name="tableGenerator",initialValue=0,allocationSize=1)
    public int getLinkid() {
        return linkid;
    }

    public void setLinkid(int linkid) {
        this.linkid = linkid;
    }

    @Basic
    @Column(name = "masterid", nullable = true, length = 30)
    public Integer getMasterid() {
        return masterid;
    }

    public void setMasterid(Integer masterid) {
        this.masterid = masterid;
    }

    @Basic
    @Column(name = "linkedname", nullable = true, length = 30)
    public String getLinkedname() {
        return linkedname;
    }

    public void setLinkedname(String linkedname) {
        this.linkedname = linkedname;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Containerlink that = (Containerlink) o;
        return linkid == that.linkid &&
                Objects.equals(masterid, that.masterid) &&
                Objects.equals(linkedname, that.linkedname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(linkid, masterid, linkedname);
    }
}

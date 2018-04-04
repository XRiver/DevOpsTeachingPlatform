package Devops.docker.DockerBranch.db;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Basicimage {
    private int basicId;
    private String name;
    private String shellScript;

    @Id
    @Column(name = "basicId")
    public int getBasicId() {
        return basicId;
    }

    public void setBasicId(int basicId) {
        this.basicId = basicId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "shellScript")
    public String getShellScript() {
        return shellScript;
    }

    public void setShellScript(String shellScript) {
        this.shellScript = shellScript;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basicimage that = (Basicimage) o;
        return basicId == that.basicId &&
                Objects.equals(name, that.name) &&
                Objects.equals(shellScript, that.shellScript);
    }

    @Override
    public int hashCode() {

        return Objects.hash(basicId, name, shellScript);
    }
}

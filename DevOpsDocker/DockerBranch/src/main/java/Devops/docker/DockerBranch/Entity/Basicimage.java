package Devops.docker.DockerBranch.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Basicimage {

    private int basicId;
    private String name;
    private String shellScript;


    @Id
    @Column(name = "basic_Id", nullable = false)
    @GeneratedValue(strategy=GenerationType.TABLE,generator="tableGenerator")
    @TableGenerator(name="tableGenerator",initialValue=0,allocationSize=1)
    public int getBasicId() {
        return basicId;
    }

    public void setBasicId(int basicId) {
        this.basicId = basicId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "shell_Script", nullable = true, length = 1000)
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

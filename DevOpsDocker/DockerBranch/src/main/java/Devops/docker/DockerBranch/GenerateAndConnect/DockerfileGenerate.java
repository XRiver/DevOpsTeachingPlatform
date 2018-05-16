package Devops.docker.DockerBranch.GenerateAndConnect;

public interface DockerfileGenerate {
    public StringBuilder getTomcatDockerfile(String fileName);

    public StringBuilder getMysqlDockerfile(String fileName);
}

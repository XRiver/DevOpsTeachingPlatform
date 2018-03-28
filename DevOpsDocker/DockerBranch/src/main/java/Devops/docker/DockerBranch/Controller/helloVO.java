package Devops.docker.DockerBranch.Controller;

public class helloVO {
	
    private final long id;
    private final String content;

    public helloVO(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

}

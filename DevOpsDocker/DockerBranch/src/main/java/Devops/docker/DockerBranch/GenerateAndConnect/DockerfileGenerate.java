package Devops.docker.DockerBranch.GenerateAndConnect;


/**
 * 生成各应用Dockerfile的类
 * author:杨关
 * */
public class DockerfileGenerate {
	
	public StringBuilder getTomcatDockerfile(String fileName) {
		
		StringBuilder result = new StringBuilder();
		result.append("FROM tomcat\r\n");
		result.append("MAINTAINER xiongkaiqi \"2948363603@qq.com\"\r\n");
		result.append("ADD "+fileName+" /usr/local/tomcat/webapps/\r\n");
		result.append("CMD [\"catalina.sh\", \"run\"]");
		
		return result;
	}

}

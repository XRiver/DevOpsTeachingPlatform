package Devops.docker.DockerBranch.GenerateAndConnect;


import org.springframework.stereotype.Service;

/**
 * 生成各应用Dockerfile的类
 * author:熊凯奇
 * */
@Service
public class DockerfileGenerateImpl implements DockerfileGenerate{
	
	public StringBuilder getTomcatDockerfile(String fileName) {
		
		StringBuilder result = new StringBuilder();
		result.append("FROM tomcat\r\n");
		result.append("MAINTAINER xiongkaiqi \"2948363603@qq.com\"\r\n");
		result.append("ADD "+fileName+" /usr/local/tomcat/webapps/\r\n");
		result.append("CMD [\"catalina.sh\", \"run\"]");
		
		return result;
	}
	
	public StringBuilder getMysqlDockerfile(String fileName) {
		StringBuilder result = new StringBuilder();
		result.append("FROM mysql:5.7\r\n");
		result.append("ENV MYSQL_ALLOW_EMPTY_PASSWORD yes\r\n");
		result.append("COPY setup.sh /mysql/setup.sh\r\n");
		result.append("COPY "+fileName+" /mysql/database.sql\r\n");
		result.append("EXPOSE 3306\r\n");
		result.append("CMD [\"sh\",\"/mysql/setup.sh\"]");
		
		return result;
	}
	


}

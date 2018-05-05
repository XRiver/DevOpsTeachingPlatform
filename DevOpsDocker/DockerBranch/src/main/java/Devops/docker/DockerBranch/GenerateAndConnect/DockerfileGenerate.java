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
	
	public StringBuilder getMysqlSetup() {
		StringBuilder result = new StringBuilder();
		result.append("set -e\r\n");
		result.append("#use to check the status of mysql can be deleted\r\n");
		result.append("echo 'service mysql status'\r\n");
		result.append("echo '1.starting mysql....'\r\n");
		result.append("service mysql start\r\n");
		result.append("sleep 3\r\n");
		result.append("echo '2.importing the data....'\r\n");
		result.append("mysql < /mysql/database.sql\r\n");
		result.append("sleep 3\r\n");
		result.append("echo '3.changing the password....'\r\n");
		result.append("# mysql < /mysql/privileges.sql\r\n");
		result.append("echo '4.password changed....'\r\n");
		result.append("sleep 3\r\n");
		result.append("tail -f /dev/null\r\n");
		return result;
	}

}

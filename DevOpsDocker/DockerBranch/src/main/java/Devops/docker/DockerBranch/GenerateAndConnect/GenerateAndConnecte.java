package Devops.docker.DockerBranch.GenerateAndConnect;

import Devops.docker.DockerBranch.Entity.Container;

/**
 * 
 * author:杨关
 * */
public class GenerateAndConnecte {
	
	/**
	 * 
	 * */
	public int Generate(Container con) {
		
		GenerateContainersService g = new GenerateContainerImpl();
		
		String image = con.getImage();
		
		if(image.equals("mysql")) {
			g.GenerateMysql(con);
		}else if(image.equals("tomcat")){
			g.GenerateTomcat(con);
		}
		
		return 0;
	}
	
}

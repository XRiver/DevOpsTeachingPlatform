package Devops.docker.DockerBranch.GenerateAndConnect;

import Devops.docker.DockerBranch.Entity.Container;

public interface GenerateContainersService {
	
	public int GenerateTomcat(Container container);
	
	public int GenerateMysql(Container container);

}

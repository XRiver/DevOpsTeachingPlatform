package Devops.docker.DockerBranch.GenerateAndConnect;

import Devops.docker.DockerBranch.Entity.Container;
import Devops.docker.DockerBranch.Entity.Host;

public interface GenerateContainersService {
	
	public int GenerateTomcat(Container container,Host host);
	
	public int GenerateMysql(Container container,Host host);

}

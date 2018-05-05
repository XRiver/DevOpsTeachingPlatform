package Devops.docker.DockerBranch.GenerateAndConnect;

import Devops.docker.DockerBranch.Entity.Container;
import Devops.docker.DockerBranch.Entity.Host;
import ch.ethz.ssh2.Connection;

public interface GenerateContainersService {
	
	public int GenerateTomcat(Container container,Host host,Connection conn);
	
	public int GenerateMysql(Container container,Host host,Connection conn);

}

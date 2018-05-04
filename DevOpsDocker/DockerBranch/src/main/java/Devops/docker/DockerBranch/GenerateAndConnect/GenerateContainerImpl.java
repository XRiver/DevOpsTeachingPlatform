package Devops.docker.DockerBranch.GenerateAndConnect;

import Devops.docker.DockerBranch.Entity.Container;
import Devops.docker.DockerBranch.FileOperator.FileCreateTools;
import Devops.docker.DockerBranch.FileOperator.FileOperateFacInstance;

public class GenerateContainerImpl implements GenerateContainersService{

	@Override
	public int GenerateTomcat(Container container) {
		// TODO Auto-generated method stub
		
		//创建文件夹
		FileOperateFacInstance FileFactory = new FileOperateFacInstance();
		FileCreateTools create = FileFactory.getCreate(true); //拿到远程创建文件的Tools
//		c.createDir("/home/ubuntu/", "testCreateDir", 0700,connection);
		
		return 0;
	}

	@Override
	public int GenerateMysql(Container container) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}

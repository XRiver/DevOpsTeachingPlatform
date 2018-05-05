package Devops.docker.DockerBranch.GenerateAndConnect;

import java.io.IOException;

import Devops.docker.DockerBranch.Entity.Container;
import Devops.docker.DockerBranch.Entity.Host;
import Devops.docker.DockerBranch.Exception.FileOperateException;
import Devops.docker.DockerBranch.FileOperator.FileCreateTools;
import Devops.docker.DockerBranch.FileOperator.FileOperateFacInstance;
import Devops.docker.DockerBranch.FileOperator.FileWriterTools;
import ch.ethz.ssh2.Connection;

public class GenerateContainerImpl implements GenerateContainersService{

	@Override
	public int GenerateTomcat(Container container,Host host,Connection conn) {
		// TODO Auto-generated method stub
//		String path = container.getPath();   //上传的文件路径
		
		String fileName = container.getFilename(); //上传的文件名字
		
//		String[] temp = fileName.split(".");
//		String DirName = temp[0];  //文件夹的名字
		
		int createFile = createFile(container,host,"Dockerfile","",conn);
		if(createFile==-1)
			return -1;
		
		//写文件
		StringBuilder Dockerfile = new DockerfileGenerate().getTomcatDockerfile(fileName);
		int writeFile = writeFile(container, host, Dockerfile,"Dockerfile","",conn);
		if(writeFile==-2)
			return -2;
		
		return 1;
	}

	@Override
	public int GenerateMysql(Container container,Host host,Connection conn) {
		// TODO Auto-generated method stub
		String fileName = container.getFilename(); //上传的文件名字
		
		//创建Dockerfile
		int createFile = createFile(container,host,"Dockerfile","",conn);
		if(createFile==-1)
			return -1;
		
		//写Dockerfile文件
		StringBuilder Dockerfile = new DockerfileGenerate().getMysqlDockerfile(fileName);
		int writeFile = writeFile(container, host, Dockerfile,"Dockerfile","",conn);
		if(writeFile==-2)
			return -2;
		
		//创建setup。sh
		int createSetup = createFile(container,host,"setup","sh",conn);
		if(createSetup==-1)
			return -1;
		//写setup。sh
		StringBuilder setUp = new DockerfileGenerate().getMysqlSetup();
		int writeSetUp = writeFile(container, host, setUp,"setup","sh",conn);
		if(writeSetUp==-2)
			return -2;
		
		return 1;
	}
	
	private int createFile(Container container,Host host,String fileName,String fileType,Connection conn) {
		String path = container.getPath();   //上传的文件路径
		FileOperateFacInstance FileFactory = new FileOperateFacInstance();
		FileCreateTools create = FileFactory.getCreate(true); //拿到远程创建文件的Tools
		try {
			create.createFile(path+"/", fileName, fileType, conn);  //创建Dockerfile
		} catch (FileOperateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();  //这里返回错误值
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();  //这里返回错误值
			return -1; //创建Dockerfile失败
		}
		return 1;
	}
	
	private int writeFile(Container container,Host host,StringBuilder contant,String fileName,String fileType,Connection conn) {
		String path = container.getPath();   //上传的文件路径
		FileOperateFacInstance FileFactory = new FileOperateFacInstance();
		FileWriterTools write = FileFactory.getWriter(true);
		try {
			write.WriteFile(path+"/", fileName, fileType, contant, conn); //将文件写进去
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();  //这里返回错误值
			return -2;  //写Dockerfile失败
		} catch (FileOperateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();  //这里返回错误值
		}	
		return 1;
	}
	

}

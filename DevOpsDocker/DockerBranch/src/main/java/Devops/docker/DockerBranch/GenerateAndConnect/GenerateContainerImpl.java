package Devops.docker.DockerBranch.GenerateAndConnect;

import java.io.IOException;

import Devops.docker.DockerBranch.Entity.Container;
import Devops.docker.DockerBranch.Entity.Host;
import Devops.docker.DockerBranch.Exception.FileOperateException;
import Devops.docker.DockerBranch.Exception.RemoteOperateException;
import Devops.docker.DockerBranch.FileOperator.FileCreateTools;
import Devops.docker.DockerBranch.FileOperator.FileOperateFacInstance;
import Devops.docker.DockerBranch.FileOperator.FileWriterTools;
import Devops.docker.DockerBranch.RemoteConnection.RemoteSignIn;
import ch.ethz.ssh2.Connection;

public class GenerateContainerImpl implements GenerateContainersService{

	@Override
	public int GenerateTomcat(Container container) {
		// TODO Auto-generated method stub
		
		Host h = new Host();
		
		
		String path = container.getPath();   //上传的文件路径
		String fileName = container.getFilename(); //上传的文件名字
		
//		String[] temp = fileName.split(".");
//		String DirName = temp[0];  //文件夹的名字
		
		//创建
		FileOperateFacInstance FileFactory = new FileOperateFacInstance();
		FileCreateTools create = FileFactory.getCreate(true); //拿到远程创建文件的Tools
		Connection con = getConnection(h);
		try {
			create.createFile(path, "Dockerfile", "", con);  //创建Dockerfile
		} catch (FileOperateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();  //这里返回错误值
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();  //这里返回错误值
		}
		con = getConnection(h);
		StringBuilder Dockerfile = new DockerfileGenerate().getTomcatDockerfile(fileName);
		FileWriterTools write = FileFactory.getWriter(true);
		try {
			write.WriteFile(path, "Dockerfile", "", Dockerfile, con); //将文件写进去
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();  //这里返回错误值
		} catch (FileOperateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();  //这里返回错误值
		}		
		
		
		return 1;
	}

	@Override
	public int GenerateMysql(Container container) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	private Connection getConnection(Host host) {
		RemoteSignIn sign = new RemoteSignIn(host.getIp(), Integer.parseInt(host.getSshPort()), host.getRoot(), host.getPassword());
		Connection connection = null;
		try {
			connection = sign.ConnectAndAuth(sign.getUSER(), sign.getPASSWORD());
		} catch (RemoteOperateException e1) {
			// TODO Auto-generated catch block
//			e1.printStackTrace();
			return null;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
//			e1.printStackTrace();
			return null;
		}
		return connection;
	}
	

}

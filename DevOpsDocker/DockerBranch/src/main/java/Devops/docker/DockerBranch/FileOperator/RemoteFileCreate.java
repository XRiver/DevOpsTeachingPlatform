package Devops.docker.DockerBranch.FileOperator;

import java.io.IOException;

import Devops.docker.DockerBranch.Exception.FileOperateException;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SFTPv3Client;

public class RemoteFileCreate extends FileCreateTools{

	@Override
	public boolean createDir(String Path, String DirName, int posixPermissions,Connection connection)
			throws FileOperateException, IOException {
		// TODO Auto-generated method stub
		
		String Dir = Path + DirName;
		
		try {
			
			SFTPv3Client sftpClient = new SFTPv3Client(connection);
			sftpClient.mkdir(Dir, posixPermissions);
			
			connection.close();
			return true;
				
		}catch (IOException e) {
			// TODO: handle exception
			connection.close();
			throw e;
		}
	}

	@Override
	public boolean createFile(String Path, String FileName, String FileType,Connection connection)
			throws FileOperateException, IOException {
		// TODO Auto-generated method stub
		
		String file = Path + FileName;
		if(FileType!=null) {
			file = file + "."+FileType;
		}
		
		try {
			
			SFTPv3Client sftpClient = new SFTPv3Client(connection);
			sftpClient.createFile(file);
			
			connection.close();
			return true;
			
		}catch (IOException e) {
			// TODO: handle exception
			connection.close();
			throw e;
		}
	}
	
//	private RemoteSignIn getTheConnection(Host host) {
//		//读取配置文件
//		String HostIP = host.getIp();
//		int HostPort = 22;
//		String HostUserName = host.getHostname();
//		String HostPassword = host.getPassword();
//		//读取配置文件
//		
//		RemoteSignIn sign = new RemoteSignIn(HostIP, HostPort, HostUserName, HostPassword);
//		
//		return sign;
//	}

}

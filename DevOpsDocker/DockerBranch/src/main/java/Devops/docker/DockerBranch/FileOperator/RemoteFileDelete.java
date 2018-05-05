package Devops.docker.DockerBranch.FileOperator;

import java.io.IOException;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SFTPv3Client;

public class RemoteFileDelete extends FileDeleteTools{

	@Override
	public boolean DeleteFile(String Path, String FileName, String FileType,Connection connection) 
			throws IOException {
		// TODO Auto-generated method stub
		
		String file = Path + FileName;
		if(!FileType.equals("")) {
			file = file + "."+FileType;
		}
		
		try {
			
			SFTPv3Client sftpClient = new SFTPv3Client(connection);
			sftpClient.rm(file);
//			connection.close();
			return true;
			
		}catch (IOException e) {
			// TODO: handle exception
			connection.close();
			throw e;
		}
	}

	@Override
	public boolean DeleteFiles(String[] filePaths,Connection connection) throws IOException {
		// TODO Auto-generated method stub
		
		try {
			
			SFTPv3Client sftpClient = new SFTPv3Client(connection);
			
			for(int i = 0 ; i< filePaths.length;i++) {
				sftpClient.rm(filePaths[i]);
			}
			
//			connection.close();
			
			return true;
		}catch (IOException e) {
			// TODO: handle exception
			connection.close();
			throw e;
		}
	}

	@Override
	public boolean DeleteRmptyDir(String Path, String DirName,Connection connection) throws IOException {
		// TODO Auto-generated method stub
		
		try {
			
			SFTPv3Client sftpClient = new SFTPv3Client(connection);
			sftpClient.rmdir(Path+DirName);
			
//			connection.close();
			
			return true;
			
		}catch(IOException e) {
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

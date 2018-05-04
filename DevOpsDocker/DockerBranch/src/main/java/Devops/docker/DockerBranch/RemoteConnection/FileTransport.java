package Devops.docker.DockerBranch.RemoteConnection;

import java.io.IOException;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;

/**
 * 
 * author:杨关
 * 作用：从远程主机获得文件或者将文件put过去
 * 输入：
 * 输出：
 * 
 *  注释：还有没有写完  应该还需要一个类   就是读取本地配置文件  拿到主机的ip port user 和 password
 * */
public class FileTransport {

	private String FileName;
	private String FileType;
	private String LocalPath;
	private String RemotePath;
	private Connection connection;
	
	
	public FileTransport(String fileName, String fileType, String localPath, String remotePath,Connection connection) {
		super();
		FileName = fileName;
		FileType = fileType;
		LocalPath = localPath;
		RemotePath = remotePath;
		this.connection = connection;
	}

	
	public void getFile() throws IOException {
		
		try {
			
			SCPClient scpClient = connection.createSCPClient();
			String remoteFile = "";
			if(FileType=="") {
				remoteFile =RemotePath + FileName;
			}else {
				remoteFile =RemotePath + FileName + "." + FileType;
			}
			scpClient.get(remoteFile, LocalPath);
		}catch (IOException e) {
			// TODO: handle exception
			connection.close();
			throw e;
		}
		connection.close();
	}

	public boolean putFile() throws IOException{
		
		try {
			
			SCPClient scpClient = connection.createSCPClient();
			String localFile = "";
			if(FileType=="") {
				localFile = LocalPath + FileName;
			}else {
				localFile = LocalPath + FileName + "." + FileType;
			}
//			scpClient.put(localFile, RemotePath);
			scpClient.put(localFile, RemotePath, "0700");
			
		}catch (IOException e) {
			// TODO: handle exception
			connection.close();
			throw e;
		}
		connection.close();
		return true;
	}

	public String getFileName() {
		return FileName;
	}


	public void setFileName(String fileName) {
		FileName = fileName;
	}


	public String getFileType() {
		return FileType;
	}


	public void setFileType(String fileType) {
		FileType = fileType;
	}


	public String getLocalPath() {
		return LocalPath;
	}


	public void setLocalPath(String localPath) {
		LocalPath = localPath;
	}


	public String getRemotePath() {
		return RemotePath;
	}


	public void setRemotePath(String remotePath) {
		RemotePath = remotePath;
	}


	public Connection getConnection() {
		return connection;
	}


	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	
	
}

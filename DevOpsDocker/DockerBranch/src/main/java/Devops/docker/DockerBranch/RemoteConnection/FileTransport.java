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
	
	
	public FileTransport(String fileName, String fileType, String localPath, String remotePath) {
		super();
		FileName = fileName;
		FileType = fileType;
		LocalPath = localPath;
		RemotePath = remotePath;
	}
	
	public RemoteSignIn SignIn() {
		//前面还有读取文件的操作
		RemoteSignIn sign = new RemoteSignIn("", 22, "", "");
		return sign;
	}
	
	public void getFile() {  //这里的实现还需要再等一等
		
	}

	public boolean putFile() {
		RemoteSignIn sign = SignIn();
		Connection connection = sign.getConnection(); //通过SignIn方法拿到Connection
		try {
			connection.connect();
			boolean isAuthed = connection.authenticateWithPassword(sign.getUSER(), sign.getPASSWORD());
			if(isAuthed) {
				System.out.println("认证成功");
				SCPClient scpClient = connection.createSCPClient();
				String localFile = "";
				if(FileType=="") {
					localFile = LocalPath + FileName;
				}else {
					localFile = LocalPath + FileName + "." + FileType;
				}
				scpClient.put(localFile, RemotePath);
			}else {
				System.out.println("认证失败");
				return false;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			connection.close();
		}
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
	
}

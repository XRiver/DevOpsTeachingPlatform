package Devops.docker.DockerBranch.FileOperator;

import java.io.IOException;

import Devops.docker.DockerBranch.Exception.RemoteOperateException;
import Devops.docker.DockerBranch.RemoteConnection.RemoteSignIn;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SFTPv3Client;
import ch.ethz.ssh2.SFTPv3FileHandle;

public class RemoteFileReader extends FileReaderTools{

	public RemoteFileReader(String Path, String FileName, String FileType) {
		super(Path, FileName, FileType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public StringBuilder ReadFile(String Path, String FileName, String FileType) 
			throws IOException, RemoteOperateException {
		// TODO Auto-generated method stub
		StringBuilder resultString = new StringBuilder();
		//这里的填写配置文件相关的读远程linux文件的IP、Port、Username、Password
		String HostIP = "119.29.88.207";
		int HostPort = 22;
		String HostUserName = "ubuntu";
		String HostPassword = "abc8879623";
		//这里的填写配置文件相关的读远程linux文件的IP、Port、Username、Password
		
		RemoteSignIn sign = new RemoteSignIn(HostIP, HostPort, HostUserName, HostPassword);
		Connection connection = sign.getConnection(); //通过SignIn方法拿到Connection
		
		String file = Path + FileName;
		if(FileType!=null) {
			file = file + "."+FileType;
		}
		
		try {
			connection.connect();
			boolean isAuthed = connection.authenticateWithPassword(sign.getUSER(), sign.getPASSWORD());
			if(isAuthed) {
				SFTPv3Client sftpClient = new SFTPv3Client(connection);
				SFTPv3FileHandle sftpHandle = sftpClient.openFileRO(file);
				
				
				byte[] bs = new byte[1];  
		        int i = 0;  
		        long offset = 0;  
		        while(i!=-1){  
		        	i = sftpClient.read(sftpHandle, offset, bs, 0, bs.length);  
		            offset += i;  
		            if(bs[0] == '\n') {
		            	resultString.append("\r\n");
		            }else {
		                resultString.append(new String(bs));
		            }
		        } 
		        connection.close();
		        return resultString;
			}else {
				System.out.println("认证失败");
				connection.close();
				throw new RemoteOperateException("认证失败！请检查账户密码是否正确！");
			}
		}catch (IOException e) {
			// TODO: handle exception
			connection.close();
			throw e;
		}
	}

}

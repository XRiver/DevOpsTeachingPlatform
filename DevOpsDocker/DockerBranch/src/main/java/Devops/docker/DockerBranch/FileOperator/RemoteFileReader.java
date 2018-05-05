package Devops.docker.DockerBranch.FileOperator;

import java.io.IOException;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SFTPv3Client;
import ch.ethz.ssh2.SFTPv3FileHandle;

public class RemoteFileReader extends FileReaderTools{

	public RemoteFileReader(String Path, String FileName, String FileType) {
		super(Path, FileName, FileType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public StringBuilder ReadFile(String Path, String FileName, String FileType,Connection connection) 
			throws IOException {
		// TODO Auto-generated method stub
		StringBuilder resultString = new StringBuilder();
		
		String file = Path + FileName;
		if(!FileType.equals("")) {
			file = file + "."+FileType;
		}
		
		try {
			
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
//		    connection.close();
		    return resultString;
		}catch (IOException e) {
			// TODO: handle exception
			connection.close();
			throw e;
		}
	}

}

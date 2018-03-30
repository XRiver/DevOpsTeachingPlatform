package Devops.docker.DockerBranch.FileOperator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * author:杨关
 * 
 * 作用：读取本地文件
 * 
 * 输入：本地文件的路径、本地文件的名字、本地文件的类型
 * 
 * 输出：返回 文件里的内容  以 StringBuilder的形式返回
 * 
 * */
public class LocalFileReader extends FileReaderTools{
	
	public LocalFileReader(String Path,String FileName,String FileType) {
		// TODO Auto-generated constructor stub
		super(Path, FileName, FileType);
	}

	@Override
	public StringBuilder ReadFile(String Path, String FileName, String FileType) {
		// TODO Auto-generated method stub
		String file = Path + FileName;
		StringBuilder resultString = new StringBuilder();
		String temp = "";
		if(FileType!="") {
			file = file + "." + FileType;
		}
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
			temp = reader.readLine();
			while(temp!=null) {
				resultString.append(temp+"\r\n");
				temp = reader.readLine();
			}
			reader.close();
			return resultString;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

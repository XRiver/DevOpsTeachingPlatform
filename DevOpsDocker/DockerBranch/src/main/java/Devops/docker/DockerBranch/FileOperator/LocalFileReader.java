package Devops.docker.DockerBranch.FileOperator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import Devops.docker.DockerBranch.Exception.FileOperateException;

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
	public StringBuilder ReadFile(String Path, String FileName, String FileType) throws IOException, FileOperateException {
		// TODO Auto-generated method stub
		String file = Path + FileName;
		StringBuilder resultString = new StringBuilder();
		String temp = "";
		if(FileType!="") {
			file = file + "." + FileType;
		}
		
		File ffile = new File(file);
		
		if(!ffile.exists())
			throw new FileOperateException("0",file + "：不存在");
		
		if(!ffile.isFile())
			throw new FileOperateException("1",file + "：不是一个文件");
		
		BufferedReader reader = new BufferedReader(new FileReader(ffile));
		temp = reader.readLine();
		while(temp!=null) {
			resultString.append(temp+"\r\n");
			temp = reader.readLine();
		}
		reader.close();
		return resultString;
	}

}

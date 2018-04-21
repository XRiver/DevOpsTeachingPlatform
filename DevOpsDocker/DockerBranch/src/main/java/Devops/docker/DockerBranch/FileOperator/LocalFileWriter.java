package Devops.docker.DockerBranch.FileOperator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Devops.docker.DockerBranch.Entity.Host;
import Devops.docker.DockerBranch.Exception.FileOperateException;

/**
 * 
 * author:杨关
 * 
 * 作用：写本地文件
 * 
 * 输入：本地文件的路径、本地文件的名字、本地文件的类型、需要写入的东西，该写入是全覆盖
 * 
 * 输出：boolean返回值，true为写成功，false为写失败
 * 
 * */
public class LocalFileWriter extends FileWriterTools{

	@Override
	public boolean WriteFile(String Path, String FileName, String FileType, StringBuilder containt,Host host) throws IOException, FileOperateException {
		// TODO Auto-generated method stub
		
		String file = Path + FileName;
		if(FileType!="") {
			file = file + "." + FileType;
		}
		
		File ffile = new File(file);
		
		if(!ffile.exists())
			throw new FileOperateException("0",file + "：不存在");
		
		if(!ffile.isFile())
			throw new FileOperateException("1",file + "：不是一个文件");
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(ffile));
		writer.write(containt.toString());
		writer.flush();
		writer.close();
		return true;
	}

}

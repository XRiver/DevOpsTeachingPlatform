package Devops.docker.DockerBranch.FileOperator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
	public boolean WriteFile(String Path, String FileName, String FileType, StringBuilder containt) {
		// TODO Auto-generated method stub
		
		String file = Path + FileName;
		if(FileType!="") {
			file = file + "." + FileType;
		}
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(file)));
			writer.write(containt.toString());
			writer.flush();
			writer.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}

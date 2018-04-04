package Devops.docker.DockerBranch.FileOperator;


/**
 * 
 * author:杨关
 * 作用：远程读文件和远程写文件的父类，子类使用多态实现？或者工厂实现
 * 
 * 
 * */
public abstract class FileWriterTools {
	
	
	public abstract boolean WriteFile(String Path,String FileName,String FileType,StringBuilder containt);


}

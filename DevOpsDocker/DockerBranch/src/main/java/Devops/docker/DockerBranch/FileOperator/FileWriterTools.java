package Devops.docker.DockerBranch.FileOperator;

import java.io.IOException;

import Devops.docker.DockerBranch.Exception.FileOperateException;
import Devops.docker.DockerBranch.Exception.RemoteOperateException;

/**
 * 
 * author:杨关
 * 作用：远程读文件和远程写文件的父类，子类使用多态实现？或者工厂实现
 * 
 * 
 * */
public abstract class FileWriterTools {
	
	
	/**
	 * 写文件里面的内容
	 * @param Path 文件的路径
	 * @param FileName 文件的名字
	 * @param FileType 文件的类型（后缀）
	 * @param containt 要写的文件内容
	 * @return boolean True写成功，false写失败
	 * 
	 * @exception FileOperateException 本地文件操作时抛出的错误，errorcode=0:文件不存在,errorcode=1:不是一个文件,errorcode=2:不能操作，被占用,errorcode=3:不是一个目录
	 * */
	public abstract boolean WriteFile(String Path,String FileName,String FileType,StringBuilder containt)
			throws IOException,FileOperateException,RemoteOperateException;


}

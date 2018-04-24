package Devops.docker.DockerBranch.FileOperator;

import java.io.IOException;

import Devops.docker.DockerBranch.Exception.FileOperateException;
import ch.ethz.ssh2.Connection;

/**
 * 
 * author:杨关
 * 文件创建父类
 * */
public abstract class FileCreateTools {

	/**
	 * 创建文件夹
	 * @param Path 文件的路径
	 * @param DirName 文件夹的名字
	 * @param posixPermissions 文件夹的权限
	 * @return boolean true->创建成功
	 * @exception FileOperateException 本地文件操作时抛出的错误，errorcode=0:文件不存在,errorcode=1:不是一个文件,errorcode=2:不能操作，被占用,errorcode=3:不是一个目录
	 * */
	public abstract boolean createDir(String Path,String DirName,int posixPermissions,Connection connection)
			throws FileOperateException,IOException;
	
	
	/**
	 * 创建文件,文件权限为可读可写，还没完善其他文件属性的创建
	 * @param Path 文件的路径
	 * @param FileName 文件名字
	 * @param FileType 文件后缀
	 * @return boolean true->创建成功
	 * @exception FileOperateException 本地文件操作时抛出的错误，errorcode=0:文件不存在,errorcode=1:不是一个文件,errorcode=2:不能操作，被占用,errorcode=3:不是一个目录
	 * */
	public abstract boolean createFile(String Path, String FileName, String FileType,Connection connection)
			throws FileOperateException,IOException;
	
}

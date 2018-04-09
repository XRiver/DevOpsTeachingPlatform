package Devops.docker.DockerBranch.FileOperator;


/**
 * 
 * author:杨关
 * 作用：远程读文件和远程写文件的父类，子类使用多态实现？或者工厂实现
 * 
 * 
 * */
public abstract class FileReaderTools {
	
	
	private String FileName;
	private String FileType;
	private String Path;
	
	public FileReaderTools(String Path,String FileName,String FileType) {
		this.FileName = FileName;
		this.FileType = FileType;
		this.Path = Path;
	}
	
	/**
	 * 读取文件里面的内容
	 * @param Path 文件的路径
	 * @param FileName 文件的名字
	 * @param FileType 文件的类型（后缀）
	 * @return StringBuilder 将文件内容读取，存在SpringBuilder里
	 * */
	public abstract StringBuilder ReadFile(String Path,String FileName,String FileType);
	

	public String getFileName() {
		return FileName;
	}

	public String getFileType() {
		return FileType;
	}

	public String getPath() {
		return Path;
	}

}

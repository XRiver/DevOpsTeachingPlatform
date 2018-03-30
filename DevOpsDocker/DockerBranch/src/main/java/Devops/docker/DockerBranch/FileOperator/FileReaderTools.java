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

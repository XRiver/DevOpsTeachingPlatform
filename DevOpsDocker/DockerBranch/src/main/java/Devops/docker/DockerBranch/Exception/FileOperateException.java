package Devops.docker.DockerBranch.Exception;


/**
 * 
 * 错误编码：
 * 0：文件或目录不存在
 * 1：不是一个文件或者目录
 * 2：目录或者文件删除失败
 * 3：创建文件或者目录失败 -->文件或者目录已经存在
 * 4：创建目标文件不是能目录
 * 5：目标文件所处目录不存在
 * 6：创建文件或者目录失败
 * 7：其他IO错误
 * 
 * */
public class FileOperateException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 错误编码
	 * */
	private String errorCode;
	
	
	/**
	 * 构造一个基本异常
	 * 
	 * */
	public FileOperateException (String message) {
		super(message);
	}
	
	
	/**
	 * 构造一个基本异常
	 * 
	 * @param errorCode 错误编码
	 * 
	 * @param message 信息描述
	 * */
	public FileOperateException (String errorCode, String message) {
		this(message);
		this.errorCode = errorCode;
	}


	public String getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	

}

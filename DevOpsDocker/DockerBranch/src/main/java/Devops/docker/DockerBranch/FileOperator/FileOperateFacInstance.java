package Devops.docker.DockerBranch.FileOperator;


/**
 * 
 * author:杨关
 * 对FileOperateFactory的实现
 * 
 * */
public class FileOperateFacInstance implements FileOperateFactory{

	@Override
	public FileReaderTools getReader(String Path,String FileName,String FileType,boolean isRemote) {
		// TODO Auto-generated method stub
		FileReaderTools Reader;
		if(isRemote) {
			Reader = new RemoteFileReader(Path, FileName, FileType);
		}else {
			Reader = new LocalFileReader(Path, FileName, FileType);
		}
		return Reader;
	}

	@Override
	public FileWriterTools getWriter(boolean isRemote) {
		// TODO Auto-generated method stub
		FileWriterTools Writer;
		if(isRemote) {
			Writer = new RemoteFileWriter();
		}else {
			Writer = new LocalFileWriter();
		}
		return Writer;
	}

	@Override
	public FileDeleteTools getDelete(boolean isRemote) {
		// TODO Auto-generated method stub
		FileDeleteTools Delete;
		if(isRemote) {
			Delete = new RemoteFileDelete();
		}else {
			Delete = new LocalFileDelete();
		}
		return Delete;
	}

	@Override
	public FileCreateTools getCreate(boolean isRemote) {
		// TODO Auto-generated method stub
		FileCreateTools Create;
		if(isRemote) {
			Create = new RemoteFileCreate();
		}else {
			Create = new LocalFileCreate();
		}
		return Create;
	}

}

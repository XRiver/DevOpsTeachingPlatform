package nju.wqy.service.impl;

import static nju.wqy.web.vo.OperationStatus.Status.FAILURE;
import static nju.wqy.web.vo.OperationStatus.Status.SUCCESS;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nju.wqy.dao.ConfigDao;
import nju.wqy.entity.ConfigData;
import nju.wqy.service.CheckService;
import nju.wqy.util.FileManager;
import nju.wqy.util.ShellManager;
import nju.wqy.web.exception.NotFoundException;
import nju.wqy.web.vo.ConfigVO;
import nju.wqy.web.vo.OperationStatus;
import nju.wqy.web.vo.wrapper.ConfigWrapper;
@Service
public class CheckServiceImpl implements CheckService{
	@Autowired
	private ConfigDao configDao;
	@Autowired
	private ConfigWrapper configWrapper;
	@Override
	public OperationStatus check(String projectKey) {
		//检查相应文件夹下是否有相应文件
		//如果有则更新一下
		//如果没有则需clone一个
		//以上都通过gitlab runner
		//根据id找到数据库中的相应配置
		ConfigData data=configDao.findOne(projectKey);
		if (data == null) {
			throw new NotFoundException("config "+projectKey+" not found");
		}
		//生成配置内容
		//List<String> config=generateConfig(data);		
		//生成配置文件，规定一个目录
		//FileManager.writeFile(".", config);
		//调用shell命令行，这里也需要告诉他目录
		boolean isSuc=startSonar(".");
		 if(isSuc) {
			 return new OperationStatus(SUCCESS);
		 }
		 return new OperationStatus(FAILURE);
		
	}

	private boolean startSonar(String filePath) {
		//根据参数生成shell脚本
		List<String> cmd=new ArrayList<String>();
		cmd.add("cd "+filePath);
		//视情况是否需要写全名
		cmd.add("sonar-scanner");
		//FileManager.writeFile("sonar-project.properties", cmd);
		//执行shell脚本
		if(ShellManager.callShellCommand("sh startSonar.sh")) {
			return true;
		}
		return false;
	}
	
	private List<String> generateConfig(ConfigData data) {
		List<String> result=new ArrayList<String>();
		result.add("sonar.projectKey="+data.getProjectKey());
		result.add("sonar.projectName="+data.getProjectName());
		result.add("sonar.projectVersion="+data.getProjectVersion());
		result.add("sonar.sources="+data.getSource());
		result.add("sonar.language="+data.getLanguage());
		result.add("sonar.sourceEncoding="+data.getSourceEncoding());
		result.add("sonar.java.binaries="+data.getJavaBinary());
		return result;
	}
}

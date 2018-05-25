package nju.wqy.service.impl;

import static nju.wqy.web.vo.OperationStatus.Status.FAILURE;
import static nju.wqy.web.vo.OperationStatus.Status.SUCCESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nju.wqy.dao.ConfigDao;
import nju.wqy.entity.ConfigData;
import nju.wqy.service.ConfigService;
import nju.wqy.web.exception.NotFoundException;
import nju.wqy.web.vo.ConfigVO;
import nju.wqy.web.vo.OperationStatus;
import nju.wqy.web.vo.wrapper.ConfigWrapper;
@Service
public class ConfigServiceImpl implements ConfigService{
	@Autowired
	private ConfigDao configDao;
	@Autowired
	private ConfigWrapper configWrapper;


	@Override
	public ConfigVO getConfig(String projectKey) {
		ConfigData data=configDao.getByProjectKey(projectKey);
		ConfigVO vo=configWrapper.configWrapper(data);
		if (vo != null) {
			return vo;
		} else {
			throw new NotFoundException("config "+projectKey+" not found");
		}
	}


	@Override
	public OperationStatus save(ConfigVO vo) {
		ConfigData data=configDao.getByProjectKey(vo.getProjectKey());
		System.out.println("sa"
				+ "na"+vo.getProjectName());
		if(data==null) {//新增
			data=new ConfigData();
//			data.setJavaBinary(vo.getJavaBinary());
			data.setLanguage(vo.getLanguage());
			data.setProjectKey(vo.getProjectKey());
			data.setProjectName(vo.getProjectName());
//			data.setProjectVersion(vo.getProjectVersion());
//			data.setSource(vo.getSource());
			data.setSourceEncoding(vo.getSourceEncoding());
			if(configDao.save(data)!=null) {
				return new OperationStatus(SUCCESS);
			}else {
				return new OperationStatus(FAILURE);
			}
		}else {//修改
			return modify(vo);
		}

	}


	@Override
	public OperationStatus modify(ConfigVO vo) {
		ConfigData data=configDao.getByProjectKey(vo.getProjectKey());
		if(data!=null) {
			data.setJavaBinary(vo.getJavaBinary());
			data.setLanguage(vo.getLanguage());
			data.setProjectKey(vo.getProjectKey());
			System.out.println("na"+vo.getProjectName());
			data.setProjectName(vo.getProjectName());
			data.setProjectVersion(vo.getProjectVersion());
			data.setSource(vo.getSource());
			data.setSourceEncoding(vo.getSourceEncoding());
			if(configDao.save(data)!=null) {
				return new OperationStatus(SUCCESS);
			}else {
				return new OperationStatus(FAILURE);
			}
		}else {
			return new OperationStatus(FAILURE);
		}
	}

}

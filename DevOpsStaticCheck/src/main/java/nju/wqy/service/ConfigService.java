package nju.wqy.service;

import nju.wqy.web.vo.ConfigVO;
import nju.wqy.web.vo.OperationStatus;

public interface ConfigService {

	ConfigVO getConfig(long id);
	OperationStatus save(ConfigVO vo);
	OperationStatus modify(ConfigVO vo);
}

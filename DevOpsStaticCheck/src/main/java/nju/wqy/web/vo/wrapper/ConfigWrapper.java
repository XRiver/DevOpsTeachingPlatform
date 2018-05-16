package nju.wqy.web.vo.wrapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import nju.wqy.entity.ConfigData;
import nju.wqy.web.vo.ConfigVO;
@Service
public class ConfigWrapper {
public ConfigVO configWrapper(ConfigData data) {
	if (data != null) {
		ConfigVO vo = new ConfigVO();
		BeanUtils.copyProperties(data, vo);
		return vo;
	} else {
		return null;
	}
}
}

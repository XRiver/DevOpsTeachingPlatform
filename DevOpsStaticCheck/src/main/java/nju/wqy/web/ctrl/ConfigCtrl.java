package nju.wqy.web.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nju.wqy.service.ConfigService;
import nju.wqy.web.vo.ConfigVO;
import nju.wqy.web.vo.OperationStatus;

/**
 * 
 * 处理用户输入的配置参数，创建，修改和查询
 *
 */
@RequestMapping(value = "staticCheck/config")
@RestController
public class ConfigCtrl {
	@Autowired
	private ConfigService configService;
	 
	/**
	 * 
	 * @param id 项目id
	 * @return
	 */
	@RequestMapping(value = "{projectKey}", method = RequestMethod.GET)
	public ConfigVO getConfig(@PathVariable String projectKey) {
		return configService.getConfig(projectKey);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public OperationStatus saveConfig(@RequestBody ConfigVO vo) {
		return configService.save(vo);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public OperationStatus modifyConfig(@RequestBody ConfigVO vo) {
		return configService.modify(vo);
	}
}

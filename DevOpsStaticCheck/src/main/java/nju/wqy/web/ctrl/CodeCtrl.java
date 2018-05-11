package nju.wqy.web.ctrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
/**
 * 获得源码
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nju.wqy.service.CodeService;
import nju.wqy.web.vo.CodeVO;

@RequestMapping(value = "staticCheck/code")
@RestController
public class CodeCtrl {
	@Autowired
	CodeService codeService;
	/**
	 * 
	 * @param key 文件路径
	 * @return
	 */

	@RequestMapping( method = RequestMethod.POST)
	public CodeVO getConfig(@RequestBody String key) {
		
		return codeService.getCodes(key);
	}
}

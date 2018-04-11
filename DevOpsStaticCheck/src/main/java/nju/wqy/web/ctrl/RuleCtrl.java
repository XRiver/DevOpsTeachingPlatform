package nju.wqy.web.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nju.wqy.service.RuleService;
import nju.wqy.web.vo.ConfigVO;
import nju.wqy.web.vo.RuleVO;

@RequestMapping(value = "staticCheck/rule")
@RestController
public class RuleCtrl {
	@Autowired
	RuleService ruleService;
	/**
	 * 规则详情，问题描述，修改建议，错误实例，正确实例，却少了所在行的代码
	 * @param key
	 * @return
	 */
	@RequestMapping(value = "{id:[0-9]*}", method = RequestMethod.GET)
	public RuleVO getRule(@PathVariable String key) {
		return ruleService.getRuleVO(key);
	}
}

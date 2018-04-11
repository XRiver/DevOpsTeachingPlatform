package nju.wqy.web.ctrl;
/**
 * 问题列表，所在文件，所在行数，message，rule
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nju.wqy.service.ProblemService;
import nju.wqy.web.vo.ConfigVO;
import nju.wqy.web.vo.ProblemVO;

@RequestMapping(value = "staticCheck/problem")
@RestController
public class ProblemCtrl {
	@Autowired
	ProblemService problemService;
	//分为bugs codeSmell vulnear
	@RequestMapping(value = "{id:[0-9]*}/{type}", method = RequestMethod.GET)
	public List<ProblemVO> getCon(@PathVariable long id,@PathVariable String type){
		return problemService.getProblem(type, id);
	}
}

package nju.wqy.web.ctrl;
/**
 * 问题列表，所在文件，所在行数，message，rule
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nju.wqy.service.ProblemService;
import nju.wqy.web.vo.PaginationVO;

@RequestMapping(value = "staticCheck/problem")
@RestController
public class ProblemCtrl {
	@Autowired
	ProblemService problemService;

	/**
	 * 
	 * @param projectKey
	 * @param type分为bugs codeSmell vulnerabilities
	 * @return
	 */
	@RequestMapping(value = "{projectKey}/{type}", method = RequestMethod.GET)
	public PaginationVO getCon(@PathVariable String  projectKey,@PathVariable String type,@RequestParam(value="offset") int offset,
			@RequestParam(value="pageSize")  int pageSize){
		return problemService.getProblem(type,projectKey,offset,pageSize);
	}
}

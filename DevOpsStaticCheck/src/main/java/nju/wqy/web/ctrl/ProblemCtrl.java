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
import nju.wqy.web.vo.PaginationVO;
import nju.wqy.web.vo.ProblemVO;

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
	public PaginationVO getCon(@PathVariable String  projectKey,@PathVariable String type){
		List<ProblemVO> pv=problemService.getProblem(type,projectKey);
		PaginationVO vo=new PaginationVO();
		vo.setRows(pv);
		vo.setTotal(pv.size());
		return vo;
	}
}

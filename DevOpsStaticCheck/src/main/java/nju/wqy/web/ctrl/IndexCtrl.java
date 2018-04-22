package nju.wqy.web.ctrl;
/**
 * 静态检查的首页
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nju.wqy.service.CheckService;
import nju.wqy.service.IndexService;
import nju.wqy.web.vo.ConfigVO;
import nju.wqy.web.vo.IndexVO;
import nju.wqy.web.vo.OperationStatus;

@RequestMapping(value = "staticCheck/statistic")
@RestController
public class IndexCtrl {
	@Autowired
	private IndexService indexService;

	@Autowired
	private CheckService checkService;

	/**静态检查项目首页，得到一些参数，如果为空，页面提示一下
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "{projectKey}", method = RequestMethod.GET)
	public IndexVO getIndexVO(@PathVariable String projectKey) {

		return indexService.getIndex(projectKey);
	}
	/**
	 * 页面开始检查按钮
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "{projectKey}",method = RequestMethod.POST)
	public OperationStatus check(@PathVariable String projectKey) {

		return checkService.check(projectKey);

	}
}

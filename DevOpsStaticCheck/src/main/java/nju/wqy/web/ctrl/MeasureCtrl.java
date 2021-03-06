package nju.wqy.web.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nju.wqy.service.MeasureService;
import nju.wqy.web.vo.HistoryVO;
import nju.wqy.web.vo.MeasureVO;

@RequestMapping(value = "staticCheck/measure")
@RestController
public class MeasureCtrl {
	@Autowired
	MeasureService measureService;
	@RequestMapping(value = "{projectKey}", method = RequestMethod.GET)
	public MeasureVO getGeneral(@PathVariable String projectKey) {
		return measureService.getMeasure(projectKey);
	}

	@RequestMapping(value = "{type}/{projectKey}", method = RequestMethod.GET)
	public List<HistoryVO> getHistory(@PathVariable String projectKey,@PathVariable String type) {
		return measureService.getHistory(projectKey, type);
	}
}

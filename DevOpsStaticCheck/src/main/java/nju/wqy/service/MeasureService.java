package nju.wqy.service;

import java.util.List;

import nju.wqy.web.vo.HistoryVO;
import nju.wqy.web.vo.MeasureVO;

public interface MeasureService {
MeasureVO getMeasure(String projectKey);
List<HistoryVO> getHistory(String projectKey,String type);
}

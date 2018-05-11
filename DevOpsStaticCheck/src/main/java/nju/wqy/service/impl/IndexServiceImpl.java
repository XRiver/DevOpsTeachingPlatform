package nju.wqy.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nju.wqy.dao.IntegratedDao;
import nju.wqy.entity.IntegratedData;
import nju.wqy.service.IndexService;
import nju.wqy.web.vo.IndexVO;
@Service
public class IndexServiceImpl implements IndexService{
	@Autowired
	IntegratedDao integratedDao;
	@Override
	public IndexVO getIndex(String projectKey) {
		IntegratedData data=integratedDao.findOne(projectKey);
		IndexVO vo=new IndexVO();
		vo.setHealthDegree(data.getHealthDegree());
		vo.setLastAnalyse(data.getLastAnalysis());
		vo.setRiskIndex(data.getRisk());
		vo.setUnresolvedProblems(data.getProblemNo());
		return vo;
	}
	
}

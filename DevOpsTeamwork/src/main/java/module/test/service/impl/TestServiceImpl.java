package module.test.service.impl;

import module.test.dao.TestMapper;
import module.test.model.Test;
import module.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
@Transactional
public class TestServiceImpl implements TestService {
	
	@Autowired
	private TestMapper testMapper;

	@Override
	public List<Test> selectId(Map<String, Object> params) {
		return testMapper.selectId(params);
	}
}

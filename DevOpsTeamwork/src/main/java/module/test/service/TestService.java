package module.test.service;

import module.test.model.Test;

import java.util.List;
import java.util.Map;


public interface TestService {
	
	List<Test> selectId(Map<String, Object> params);
	

}

package module.test.dao;

import module.test.model.Test;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TestMapper {
	
	 List<Test> selectId(Map<String, Object> params);

}

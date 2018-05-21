package com.Service;

import com.DataVO.TestCaseVO;

/**
 * Created by Administrator on 2018/3/20.
 */
public interface TestCaseService {
    public boolean createTestCase(TestCaseVO testCaseVO,Long testId);

    public boolean deleteTestCase(Long id);

    public boolean updateTestCase(TestCaseVO testCaseVO);

    public TestCaseVO getTestCaseById(Long id);
}

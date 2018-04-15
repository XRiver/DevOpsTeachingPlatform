package com.Controller;

import com.DataVO.MyResponseData;
import com.DataVO.TestCaseVO;
import com.Service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2018/3/23.
 */
@RestController
public class TestCaseController {
    @Autowired
    TestCaseService testCaseService;

    @RequestMapping(value = "/testcase/create", method = RequestMethod.POST)
    public MyResponseData<Boolean> createTestCase(@RequestParam("testcase") TestCaseVO testcaseVO,@RequestParam("testId") long testId){
        testCaseService.createTestCase(testcaseVO,testId);
        return new MyResponseData<Boolean>("succeed", new String[]{"成功创建测试用例！"}, true);
    }

    @RequestMapping(value = "/testcase/delete", method = RequestMethod.POST)
    public MyResponseData<Boolean> deleteTestCase(@RequestParam("id") long id){
        testCaseService.deleteTestCase(id);
        return new MyResponseData<Boolean>("succeed", new String[]{"成功删除测试用例！"}, true);
    }

    @RequestMapping(value = "/testcase/update", method = RequestMethod.POST)
    public MyResponseData<Boolean> updateTestCase(@RequestBody TestCaseVO testcaseVO){
        testCaseService.updateTestCase(testcaseVO);
        return new MyResponseData<Boolean>("succeed", new String[]{"成功更新测试用例！"}, true);
    }

    @RequestMapping(value = "/testcase/get", method = RequestMethod.GET)
    public TestCaseVO getTestCaseById(@RequestParam("id") long id){
        TestCaseVO testcase=testCaseService.getTestCaseById(id);
        return testcase;
    }
    
}

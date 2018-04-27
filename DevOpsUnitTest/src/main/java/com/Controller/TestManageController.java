package com.Controller;

import com.DataVO.MyResponseData;
import com.DataVO.TestVO;
import com.Service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2018/3/23.
 */
@RestController
public class TestManageController {
    @Autowired
    TestService testService;

    @RequestMapping(value = "/test/create", method = RequestMethod.POST)
    public MyResponseData<Boolean> createTest(@RequestParam("testId") String testId,@RequestParam("name") String name,
                                              @RequestParam("lan") String lan,@RequestParam("branch") String branch,
                                              @RequestParam("src") String src,@RequestParam("projectId") String projectId){
        TestVO testVO=new TestVO();
        testVO.setProject_id(projectId);
        testVO.setTestId(testId);
        testVO.setSrc(src);
        testVO.setName(name);
        testVO.setLanguage(lan);
        testVO.setBranch(branch);
        testService.createTest(testVO);
        return new MyResponseData<Boolean>("succeed", new String[]{"成功创建测试！"}, true);
    }

    @RequestMapping(value = "/test/delete", method = RequestMethod.POST)
    public MyResponseData<Boolean> deleteTest(@RequestParam("id") long id){
        testService.deleteTest(id);
        return new MyResponseData<Boolean>("succeed", new String[]{"成功删除测试！"}, true);
    }

    @RequestMapping(value = "/test/update", method = RequestMethod.POST)
    public MyResponseData<Boolean> updateTest(@RequestParam("testId") String testId,@RequestParam("name") String name,
                                              @RequestParam("lan") String lan,@RequestParam("branch") String branch,
                                              @RequestParam("src") String src,@RequestParam("id") long id){
        TestVO testVO=new TestVO();
        testVO.setId(id);
        testVO.setTestId(testId);
        testVO.setSrc(src);
        testVO.setName(name);
        testVO.setLanguage(lan);
        testVO.setBranch(branch);
        testService.updateTest(testVO);
        return new MyResponseData<Boolean>("succeed", new String[]{"成功更新测试！"}, true);
    }

    @RequestMapping(value = "/test/get-by-project", method = RequestMethod.GET)
    public List<TestVO> getTestByProject(@RequestParam("projectId") String id){
        List<TestVO> tests=testService.getTestByProject(id);
        return tests;
    }

    @RequestMapping(value = "/test/get", method = RequestMethod.GET)
    public TestVO getTestById(@RequestParam("id") long id){
        TestVO test=testService.getTestById(id);
        return test;
    }
}

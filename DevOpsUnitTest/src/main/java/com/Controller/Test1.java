package com.Controller;

import com.Common.BugImp;
import com.Common.BugState;
import com.Common.Language;
import com.DataVO.*;
import com.Service.BugService;
import com.Service.TestCaseService;
import com.Service.TestExecuteService;
import com.Service.TestService;
import com.util.CloneManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Test1 {
    @Autowired
    TestService testService;
    @Autowired
    TestCaseService testCaseService;
    @Autowired
    TestExecuteService testExecuteService;
    @Autowired
    BugService bugService;

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public MyResponseData<Boolean> createTest(){
        TestVO testVO=new TestVO();
        testVO.setProject_id("001");
        testVO.setName("aaa");
        testVO.setTestId("01");
        testVO.setSrc("");
        testVO.setLanguage("java");
        testVO.setBranch("master");
        testService.createTest(testVO);
        return new MyResponseData<Boolean>("succeed", new String[]{"成功创建测试！"}, true);
    }

    @RequestMapping(value = "/testcase1", method = RequestMethod.GET)
    public MyResponseData<Boolean> createTestCase(){
        long testId=testService.getTestByProject("001").get(0).getId();
        TestCaseVO testCaseVO=new TestCaseVO();
        testCaseVO.setCaseId("00001");
        testCaseVO.setName("aas");
        testCaseService.createTestCase(testCaseVO,testId);
        return new MyResponseData<Boolean>("succeed", new String[]{"成功创建测试用例！"}, true);
    }


    @RequestMapping(value = "/execute-all1", method = RequestMethod.POST)
    public ReportVO TestAll(){
        long id=1;
        String username="aaa";
        String lan=testService.getTestById(id).getLanguage();
        String projectId=testService.getTestById(id).getProject_id();
        String branch=testService.getTestById(id).getBranch();
        String url="https://github.com/terminuskyuu/helloTest.git";
        String path= CloneManager.cloneRepo(url,branch);
        if(path==null){
            return null;
        }
        if(lan.equalsIgnoreCase(Language.java.toString())){
            return testExecuteService.javaTestAll(path,id,username);

        }else if(lan.equalsIgnoreCase(Language.python.toString())){
            return testExecuteService.pythonTestAll(path,id,username);
        }else if(lan.equalsIgnoreCase(Language.c.toString())){
            return testExecuteService.cTestAll(path,id,username);
        }else{
            return null;
        }


    }

    @RequestMapping(value = "/testpy", method = RequestMethod.GET)
    public MyResponseData<Boolean> createTestpy(){
        TestVO testVO=new TestVO();
        testVO.setProject_id("001");
        testVO.setName("aaa");
        testVO.setTestId("01");
        testVO.setSrc("");
        testVO.setLanguage("python");
        testVO.setBranch("master");
        testService.createTest(testVO);
        return new MyResponseData<Boolean>("succeed", new String[]{"成功创建测试！"}, true);
    }

    @RequestMapping(value = "/executepy1", method = RequestMethod.POST)
    public ReportVO Testpy(){
        long id=1;
        String username="aaa";
        String lan=testService.getTestById(id).getLanguage();
        String projectId=testService.getTestById(id).getProject_id();
        String branch=testService.getTestById(id).getBranch();
        String url="https://github.com/terminuskyuu/pyunittest.git";
        String path= CloneManager.cloneRepo(url,branch);
        if(path==null){
            return null;
        }
        if(lan.equalsIgnoreCase(Language.java.toString())){
            return testExecuteService.javaTestAll(path,id,username);

        }else if(lan.equalsIgnoreCase(Language.python.toString())){
            return testExecuteService.pythonTestAll(path,id,username);
        }else if(lan.equalsIgnoreCase(Language.c.toString())){
            return testExecuteService.cTestAll(path,id,username);
        }else{
            return null;
        }


    }

    @RequestMapping(value = "/testc", method = RequestMethod.GET)
    public MyResponseData<Boolean> createTestc(){
        TestVO testVO=new TestVO();
        testVO.setProject_id("001");
        testVO.setName("aaa");
        testVO.setTestId("01");
        testVO.setSrc("test");
        testVO.setLanguage("c");
        testVO.setBranch("master");
        testService.createTest(testVO);
        return new MyResponseData<Boolean>("succeed", new String[]{"成功创建测试！"}, true);
    }

    @RequestMapping(value = "/executec1", method = RequestMethod.POST)
    public ReportVO Testc(){
        long id=2;
        String username="aaa";
        String lan=testService.getTestById(id).getLanguage();
        String projectId=testService.getTestById(id).getProject_id();
        String branch=testService.getTestById(id).getBranch();
        String url="https://github.com/terminuskyuu/cunittest.git";
        String path= CloneManager.cloneRepo(url,branch);
        if(path==null){
            return null;
        }
        if(lan.equalsIgnoreCase(Language.java.toString())){
            return testExecuteService.javaTestAll(path,id,username);

        }else if(lan.equalsIgnoreCase(Language.python.toString())){
            return testExecuteService.pythonTestAll(path,id,username);
        }else if(lan.equalsIgnoreCase(Language.c.toString())){
            return testExecuteService.cTestAll(path,id,username);
        }else{
            return null;
        }


    }



    @RequestMapping(value = "/bug1", method = RequestMethod.GET)
    public MyResponseData<Boolean> createBug(){
        BugVO bugVO=new BugVO();
        bugVO.setProject_id("001");
        bugVO.setImportance(BugImp.common.toString());
        bugVO.setName("asdas");
        bugVO.setInfo("asasqqqqqqqq");
        bugVO.setState(BugState.newbuilt.toString());
        bugService.createBug(bugVO);
        return new MyResponseData<Boolean>("succeed", new String[]{"成功创建缺陷！"}, true);
    }

    @RequestMapping(value = "/bug/change1", method = RequestMethod.POST)
    public MyResponseData<Boolean> changeBug(){
        long id=1;
        BugChangeVO bugChangeVO=new BugChangeVO();
        bugChangeVO.setAfter_state(BugState.closed.toString());
        bugChangeVO.setInfo("asdasd");
        bugService.createBugChange(bugChangeVO,id);
        return new MyResponseData<Boolean>("succeed", new String[]{"成功更改缺陷！"}, true);
    }

}

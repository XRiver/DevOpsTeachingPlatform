package com.Controller;

import com.Common.BugImp;
import com.Common.BugState;
import com.Common.Language;
import com.DataVO.*;
import com.Service.*;
import com.util.CloneManager;
import com.util.ShellCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    ReportService reportService;
    @Autowired
    ApiCallService apiCallService;

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


    @RequestMapping(value = "/executejava-all", method = RequestMethod.POST)
    public ReportVO TestAll(){
        long id=1;
        String username="aaa";
        String projectId=testService.getTestById(id).getProject_id();
        String branch=testService.getTestById(id).getBranch();
        String url="https://github.com/terminuskyuu/helloTest.git";
        String path= CloneManager.cloneRepo(url,branch);
        if(path==null){
            return null;
        }

            ReportVO reportVO=testExecuteService.javaTestAll(path,id,username);
            ShellCommand.clearDir(path);
            return reportVO;



    }

    @RequestMapping(value = "/executejava-files", method = RequestMethod.POST)
    public ReportVO TestjavaPart(){
        long id=1;
        String username="aaa";
        String projectId=testService.getTestById(id).getProject_id();
        String branch=testService.getTestById(id).getBranch();
        String url="https://github.com/terminuskyuu/helloTest.git";
        String path= CloneManager.cloneRepo(url,branch);
        if(path==null){
            return null;
        }
        List<String> file=new ArrayList<String>();
        file.add("HelloTest");
        file.add("Hello3Test");

        return testExecuteService.javaTest(path,file,id,username);



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
        String projectId=testService.getTestById(id).getProject_id();
        String branch=testService.getTestById(id).getBranch();
        String url="https://github.com/terminuskyuu/pyunittest.git";
        String path= CloneManager.cloneRepo(url,branch);
        if(path==null){
            return null;
        }
        ReportVO reportVO=testExecuteService.pythonTestAll(path,id,username);
        ShellCommand.clearDir(path);
        return reportVO;


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
        long id=1;
        String username="aaa";
        String lan=testService.getTestById(id).getLanguage();
        String projectId=testService.getTestById(id).getProject_id();
        String branch=testService.getTestById(id).getBranch();
        String url="https://github.com/terminuskyuu/cunittest.git";
        String path= CloneManager.cloneRepo(url,branch);
        if(path==null){
            return null;
        }
        ReportVO reportVO=testExecuteService.cTestAll(path,id,username);
        ShellCommand.clearDir(path);
        return reportVO;




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

    @RequestMapping(value = "/report1", method = RequestMethod.GET)
    public MyResponseData<Boolean> report(){
        FaultInfoVO faultInfoVO=new FaultInfoVO();
        ReportVO reportVO=new ReportVO();
        reportVO.setCase_num(3);
        reportVO.setFail_num(2);
        reportVO.setSucess_num(1);
        faultInfoVO.setCase_name("assasa");
        faultInfoVO.setFunc_name("func1");
        faultInfoVO.setLine(11);
        faultInfoVO.setType("ssss error");
        List<FaultInfoVO> list=new ArrayList<FaultInfoVO>();
        list.add(faultInfoVO);
        reportVO.setFault_info(list);

        reportService.createReport(reportVO,new Long(1));
        return new MyResponseData<Boolean>("succeed", new String[]{"成功更改缺陷！"}, true);
    }

    @RequestMapping(value = "/report1", method = RequestMethod.POST)
    public ReportVO reportget(){

        return reportService.getReportById(new Long(1));
    }

    @RequestMapping(value = "/url1", method = RequestMethod.GET)
    public String urlget(){

        return apiCallService.getUrl("1");
    }

}

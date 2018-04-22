package com.Controller;

import com.Common.Language;
import com.DataVO.ReportVO;
import com.Service.ApiCallService;
import com.Service.TestExecuteService;
import com.Service.TestService;
import com.util.CloneManager;
import com.util.ShellCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2018/3/23.
 */
@RestController
public class TestExecuteController {
    @Autowired
    TestService testService;
    @Autowired
    TestExecuteService testExecuteService;
    @Autowired
    private ApiCallService apiCallService;

    @RequestMapping(value = "/test/execute-all", method = RequestMethod.POST)
    public ReportVO TestAll(@RequestParam("id") long id,@RequestParam ("username") String username){

        String lan=testService.getTestById(id).getLanguage();
        String projectId=testService.getTestById(id).getProject_id();
        String branch=testService.getTestById(id).getBranch();
        String url= apiCallService.getUrl(projectId);
        String path= CloneManager.cloneRepo(url,branch);

        ReportVO reportVO;
        if(path==null){
            return null;
        }
        if(lan.equalsIgnoreCase(Language.java.toString())){
            reportVO=testExecuteService.javaTestAll(path,id,username);

        }else if(lan.equalsIgnoreCase(Language.python.toString())){
            reportVO=testExecuteService.pythonTestAll(path,id,username);
        }else if(lan.equalsIgnoreCase(Language.c.toString())){
            reportVO=testExecuteService.cTestAll(path,id,username);
        }else{
            return null;
        }
        if(path.length()>1){
            ShellCommand.clearDir(path);
        }

        return reportVO;
    }

    @RequestMapping(value = "/test/execute", method = RequestMethod.POST)
    public ReportVO executeTest(@RequestParam("id") long id,@RequestParam("file") List<String> file,@RequestParam ("username") String username){
        String lan=testService.getTestById(id).getLanguage();
        String projectId=testService.getTestById(id).getProject_id();
        String branch=testService.getTestById(id).getBranch();
        String url= apiCallService.getUrl(projectId);
        String path= CloneManager.cloneRepo(url,branch);

        ReportVO reportVO;
        if(path==null){
            return null;
        }
        if(lan.equalsIgnoreCase(Language.java.toString())){
            reportVO=testExecuteService.javaTest(path,file,id,username);

        }else if(lan.equalsIgnoreCase(Language.python.toString())){
            reportVO=testExecuteService.pythonTest(path,file,id,username);
        }else if(lan.equalsIgnoreCase(Language.c.toString())){
            reportVO=testExecuteService.cTest(path,file,id,username);
        }else{
            return null;
        }
        if(path.length()>1){
            ShellCommand.clearDir(path);
        }

        return reportVO;

    }


}

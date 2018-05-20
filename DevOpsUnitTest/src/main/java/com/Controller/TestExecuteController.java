package com.Controller;

import com.Common.Language;
import com.DataVO.ReportVO;
import com.Service.ApiCallService;
import com.Service.ReportService;
import com.Service.TestExecuteService;
import com.Service.TestService;
import com.util.CloneManager;
import com.util.ShellCommand;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @Autowired
    ReportService reportService;

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
    public ReportVO executeTest(@RequestParam("id") long id,@RequestParam("file") String file,@RequestParam ("username") String username){
        List<String> files=new ArrayList<String>();
        System.out.println(file);
        Object json = new JSONTokener(file).nextValue();
        if(json instanceof JSONArray){
            JSONArray jsonArray = (JSONArray)json;
            for(int i=0;i<jsonArray.length();i++){
                String temp=(String) jsonArray.get(i);
                files.add(temp);
                System.out.println(temp);
            }
        }else{
            files.add(file);
            System.out.println(file);
        }
        /**
        JSONArray jsonArray=new JSONArray(file);
        for(int i=0;i<jsonArray.length();i++){
            String temp=(String) jsonArray.get(i);
            files.add(temp);
            System.out.println(temp);
        }
         */
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
            reportVO=testExecuteService.javaTest(path,files,id,username);

        }else if(lan.equalsIgnoreCase(Language.python.toString())){
            reportVO=testExecuteService.pythonTest(path,files,id,username);
        }else if(lan.equalsIgnoreCase(Language.c.toString())){
            reportVO=testExecuteService.cTest(path,files,id,username);
        }else{
            return null;
        }
        if(path.length()>1){
            ShellCommand.clearDir(path);
        }

        return reportVO;

    }


}

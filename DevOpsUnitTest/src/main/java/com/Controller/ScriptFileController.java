package com.Controller;

import com.DataVO.ReportVO;
import com.Entity.TestEntity;
import com.Service.ApiCallService;
import com.Service.PipelineService;
import com.Service.ScriptFileService;
import com.Service.TestService;
import com.util.CloneManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScriptFileController {
    @Autowired
    private ScriptFileService scriptFileService;
    @Autowired
    private TestService testService;
    @Autowired
    private ApiCallService apiCallService;
    @Autowired
    private PipelineService pipelineService;

    @RequestMapping(value = "/test/create-script", method = RequestMethod.POST)
    public boolean TestAllScript(@RequestParam("id") long id){
        String projectId=testService.getTestById(id).getProject_id();
        String branch=testService.getTestById(id).getBranch();
        String url= apiCallService.getUrl(projectId);
        String path= CloneManager.cloneRepo(url,branch);
        return scriptFileService.uploadScript(path,id);

    }

    @RequestMapping(value = "/test/pipeline-script", method = RequestMethod.POST)
    public boolean pipelineScript(@RequestParam("group") String group ,@RequestParam("project") String project){
        return scriptFileService.pipelineScript(group, project);

    }


    @RequestMapping(value = "/starttest", method = RequestMethod.POST)
    public ReportVO PipelineTestXml(@RequestParam("group") String group ,@RequestParam("project") String project){
        return pipelineService.pipelineReport(group, project);

    }


}

package com.interstellar.devopsjenkins.controller;

import com.interstellar.devopsjenkins.FeignClient.JenkinsFeign;
import com.interstellar.devopsjenkins.FeignClient.JenkinsFeign1;
import com.interstellar.devopsjenkins.service.JenkinsService;
import com.interstellar.devopsjenkins.util.ResultMessage;
import com.interstellar.devopsjenkins.vo.BuildInformationVO;
import com.interstellar.devopsjenkins.vo.JobInformationVO;
import com.interstellar.devopsjenkins.vo.NodeInformationVO;
import com.interstellar.devopsjenkins.vo.StepVO;
import com.offbytwo.jenkins.JenkinsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/jj")
public class JenkinsController {
    @Autowired
    JenkinsFeign jenkinsFeign;
    @Autowired
    JenkinsService jenkinsService;
    @Autowired
    JenkinsServer jenkinsServer;
@Autowired
    JenkinsFeign1 jenkinsFeign1;


    @RequestMapping(value = "/job/{name}", method = RequestMethod.GET)
    @ResponseBody
    public JobInformationVO getJob(@PathVariable("name") String name) throws IOException {
        JobInformationVO jobInformationVO = jenkinsService.getJob(name);
        System.out.println();
        System.out.println();
        //System.out.println(jobInformationVO.getName() + "   " + jobInformationVO.getUrl());
        System.out.println();
        System.out.println();
        return jobInformationVO;
    }

    @RequestMapping(value = "/job/{name}/build", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage buildJob(@PathVariable("name") String name) {
       /* String result = jenkinsFeign.Build(name);
        System.out.println();
        System.out.println();
        System.out.println("result is:" + result + "!!!");
        System.out.println();
        System.out.println();
        if (result.equals("")) {
            return new ResultMessage(true, "构建成功", null);
        }*/
        try {
            ResultMessage resultMessage = jenkinsService.buildJob(name);
            return resultMessage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMessage(false, "构建失败", null);
    }

    @RequestMapping(value = "/job/{name}", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage createJob(@PathVariable("name") String name, String description, String url, String branch, String jenkinsFilePath) {
        try {
            return jenkinsService.createJob(name, description, url, branch, jenkinsFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMessage(false, "创建失败", null);
    }

    @RequestMapping(value = "/job/{name}/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage updateJob(@PathVariable("name") String name, String description, String url, String branch, String jenkinsFilePath) {
        try {
            return jenkinsService.updateJob(name, description, url, branch, jenkinsFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMessage(false, "更新失败", null);
    }

    @RequestMapping(value = "/job/{name}/period", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage periodJob(@PathVariable("name") String name, Integer period) {
        try {
            return jenkinsService.periodJob(name, period);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMessage(false, "更新周期失败", null);
    }

    @RequestMapping(value = "/job/{name}/doDelete", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage deleteJob(@PathVariable("name") String name) {
        try {
            return jenkinsService.deleteJob(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMessage(false, "删除失败", null);
    }

    @RequestMapping(value = "/job/{name}/build/{number}/nodes", method = RequestMethod.GET)
    @ResponseBody
    public List<NodeInformationVO> getNodes(@PathVariable("name") String name, @PathVariable("number") String number) {
        return jenkinsFeign.pipelineNodes(name, number);
    }

    @RequestMapping(value = "/job/{name}/build/{number}/nodes/{nodeId}", method = RequestMethod.GET)
    @ResponseBody
    public NodeInformationVO getNode(@PathVariable("name") String name, @PathVariable("number") String number, @PathVariable("nodeId") String nodeId) {
        return jenkinsFeign.pipelineNode(name, number, nodeId);
    }

    @RequestMapping(value = "/job/{name}/build/{number}/nodes/{nodeId}/steps", method = RequestMethod.GET)
    @ResponseBody
    public List<StepVO> getNodeSteps(@PathVariable("name") String name, @PathVariable("number") String number, @PathVariable("nodeId") String nodeId) {
        return jenkinsFeign.pipelineNodeSteps(name, number, nodeId);
    }

    @RequestMapping(value = "/job/{name}/build/{number}", method = RequestMethod.GET)
    @ResponseBody
    public BuildInformationVO getBuild(@PathVariable("name") String name, @PathVariable("number") String number) throws IOException {
        return jenkinsService.getBuild(name, number);
    }

    @RequestMapping(value = "/job/{name}/build/{number}/nodes/{nodeId}/steps/{stepId}", method = RequestMethod.GET)
    @ResponseBody
    public List<StepVO> getStep(@PathVariable("name") String name, @PathVariable("number") String number, @PathVariable("nodeId") String nodeId, @PathVariable("stepId") String stepId) {
        return jenkinsFeign.pipelineStep(name, number, nodeId, stepId);
    }


    @RequestMapping(value = "/job/{name}/build/{number}/download", method = RequestMethod.GET)
    @ResponseBody
    public String downloadLog(@PathVariable("name") String name, @PathVariable("number") String number) throws IOException {

        return jenkinsFeign1.downloadLog(name,number);
    }
}

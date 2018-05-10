package com.interstellar.devopsjenkins.controller;

import com.interstellar.devopsjenkins.FeignClient.JenkinsFeign;
import com.interstellar.devopsjenkins.FeignClient.JenkinsFeign1;
import com.interstellar.devopsjenkins.FeignClient.JenkinsFeign2;
import com.interstellar.devopsjenkins.service.JenkinsService;
import com.interstellar.devopsjenkins.util.ResultMessage;
import com.interstellar.devopsjenkins.util.jenkinsURL;
import com.interstellar.devopsjenkins.vo.*;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Controller
@RequestMapping("/jenkins")
public class JenkinsController {
    @Autowired
    JenkinsFeign jenkinsFeign;
    @Autowired
    JenkinsService jenkinsService;
    @Autowired
    JenkinsServer jenkinsServer;
    @Autowired
    JenkinsFeign1 jenkinsFeign1;
    @Autowired
    JenkinsFeign2 jenkinsFeign2;


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

    @RequestMapping(value = "/job/{name}/create", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage createJob1(@PathVariable("name") String name, String description, String url, String branch, String jenkinsFilePath) {
        StringBuffer sb = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("/home/bastilashan/config.xml")));
            String result = "";
            while ((result = br.readLine()) != null) {
                sb.append(result);

            }
            ;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        jenkinsFeign2.createItem(sb.toString().getBytes(), name);
        return new ResultMessage(false, "创建失败", null);
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
    public StepVO getStep(@PathVariable("name") String name, @PathVariable("number") String number, @PathVariable("nodeId") String nodeId, @PathVariable("stepId") String stepId) {
        return jenkinsFeign.pipelineStep(name, number, nodeId, stepId);
    }


    @RequestMapping(value = "/job/{name}/build/{number}/log", method = RequestMethod.GET)
    @ResponseBody
    public String getLog(@PathVariable("name") String name, @PathVariable("number") String number) throws IOException {

        return jenkinsFeign1.getLog(name, number);
    }

    @RequestMapping(value = "/job/{name}/build/{number}/log/download", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> downloadLog(@PathVariable("name") String name, @PathVariable("number") String number) throws IOException {
        ResponseEntity<byte[]> entity = null;
        HttpHeaders headers = new HttpHeaders();
        String fileName = "log.txt";
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.TEXT_PLAIN);
        HttpStatus statusCode = HttpStatus.OK;
        String result = jenkinsFeign1.downloadLog(name, number);
        byte[] bytes = result.getBytes();
        entity = new ResponseEntity<byte[]>(bytes, headers, statusCode);
        return entity;
    }

    @RequestMapping(value = "/job/{name}/build/{number}/nodes/{nodeId}/steps/{stepId}/log", method = RequestMethod.GET)
    @ResponseBody
    public String getStepLog(@PathVariable("name") String name, @PathVariable("number") String number, @PathVariable("nodeId") String nodeId, @PathVariable("stepId") String stepId) throws IOException {

        return jenkinsFeign1.stepLog(name, number, nodeId, stepId);
    }

    @RequestMapping(value = "/job/{name}/download", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> downloadArtifact(@PathVariable("name") String name) {
        ResponseEntity<byte[]> entity = null;
        HttpHeaders headers = new HttpHeaders();
        String fileName = "artifact.zip";
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        HttpStatus statusCode = HttpStatus.OK;
        try {
            jenkinsServer.getJob(name).getLastSuccessfulBuild();
            ResponseEntity<byte[]> bytes = jenkinsFeign2.artifact(name);
            byte[] bytes1 = bytes.getBody();
            entity = new ResponseEntity<byte[]>(bytes1, headers, statusCode);
            return entity;
        } catch (IOException e) {
            e.getMessage();
            return entity;
        }
    }

    @RequestMapping(value = "/computers", method = RequestMethod.GET)
    @ResponseBody
    public List<ComputerVO> getComputer() throws IOException {
        return jenkinsService.getComputers();
    }

    @RequestMapping(value = "/createCredentials", method = RequestMethod.POST)
    @ResponseBody
    public void createCre(String id, String username, String privateKey) throws URISyntaxException, IOException {
        JenkinsHttpClient client = new JenkinsHttpClient(new URI(jenkinsURL.getJenkins()), jenkinsURL.getName(), jenkinsURL.getPassword());
        
        String json = "json={\"\":\"0\",\"credentials\":{\"scope\":\"GLOBAL\",\"id\":\"" + id + "\",\"username\":\"" + username + "\",\"password\":\"\",\"privateKeySource\":{\"stapler-class\":\"com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey$DirectEntryPrivateKeySource\",\"privateKey\":\"" + privateKey + "\",},\"description\":\"\",\"stapler-class\":\"com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey\"}}";
        String req = "json={\"\":\"0\",\"credentials\":{\"scope\":\"GLOBAL\",\"username\":\"" + username + "\",\"password\":\"\",\"id\":\"" + id + "\",\"description\":\"\",\"$class\":\"com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl\"}}";
        //client.post_text("credentials/store/system/domain/_/createCredentials",req, ContentType.APPLICATION_FORM_URLENCODED,true);
        client.post_text("credentials/store/system/domain/_/createCredentials", json, ContentType.APPLICATION_FORM_URLENCODED, false);
    }
}

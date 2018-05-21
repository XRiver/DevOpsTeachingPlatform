package com.interstellar.devopsjenkins.service.impl;


import com.interstellar.devopsjenkins.FeignClient.GitLabFeign;
import com.interstellar.devopsjenkins.service.JenkinsService;
import com.interstellar.devopsjenkins.util.ResultMessage;
import com.interstellar.devopsjenkins.util.jenkinsURL;
import com.interstellar.devopsjenkins.vo.BuildInformationVO;
import com.interstellar.devopsjenkins.vo.BuildVO;
import com.interstellar.devopsjenkins.vo.JobInformationVO;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import com.offbytwo.jenkins.model.Build;
import com.offbytwo.jenkins.model.BuildWithDetails;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JenkinsServiceImpl implements JenkinsService {
    @Autowired
    JenkinsServer jenkinsServer;
    @Autowired
    GitLabFeign gitLabFeign;

    @Override
    public ResultMessage createJob(String name, String description, String url, String jenkinsfilePath) throws IOException {

        if (jenkinsServer.getJob(name) != null) {
            return new ResultMessage(false, "已存在名为" + name + "的项目", null);
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true);
        try {
            // 从XML文档中获取DOM文档实例
            DocumentBuilder db = dbf.newDocumentBuilder();
            // 获取Document对象
            Document xmldoc = db.parse("/home/xujianghe/jenkins/config.xml");

            // 获取根节点
            Element root = xmldoc.getDocumentElement();
            // 定位id为001的节点

            // 将age节点的内容更改为28
            root.getElementsByTagName("description").item(0).setTextContent(description);

            //root.getElementsByTagName("name").item(0).setTextContent(branch);
            root.getElementsByTagName("scriptPath").item(0).setTextContent(jenkinsfilePath);


            root.getElementsByTagName("remote").item(0).setTextContent(url);
            //root.getElementsByTagName("credentialsId").item(0).setTextContent(name);
            // 保存
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer former = factory.newTransformer();
            former.transform(new DOMSource(xmldoc), new StreamResult(new File("/home/xujianghe/jenkins/config/config-" + name + "-.xml")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder build = new StringBuilder();
        try {
            InputStream in = new FileInputStream("/home/xujianghe/jenkins/config/config-" + name + "-.xml");
            InputStreamReader read = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                build.append(lineTxt);
            }

            jenkinsServer.createJob(name, build.toString(), false);
            return new ResultMessage(true, "创建成功", getJob(name));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(false, "创建失败", null);
        }

        /*String []cmds = {"curl","-i","-H","Content-Type:application/xml","-X","POST","admin:qwe1996222@localhost:8080/createItem?name="+name,"--data-binary","@/home/bastilashan/config-"+name+"-.xml"};
        ProcessBuilder pb=new ProcessBuilder(cmds);
        pb.redirectErrorStream(true);
        Process p;
        try {
            p = pb.start();
            BufferedReader br=null;
            String line=null;
            StringBuffer sb=new StringBuffer("");
            br=new BufferedReader(new InputStreamReader(p.getInputStream()));
            while((line=br.readLine())!=null){
                sb.append(line);
            }
            br.close();
            return new ResultMessage(true,"创建成功",sb.toString());
        } catch (IOException e) {

            e.printStackTrace();
        }
            return new ResultMessage(false,"创建失败",null);*/
    }

    @Override
    public ResultMessage buildJob(String name) {

        try {
            Job job = jenkinsServer.getJob(name);
            job.build();
            //JobWithDetails jobWithDetails = job.details();
            //Build build = jobWithDetails.getLastBuild();

            //BuildVO vo = new BuildVO(build.getNumber(), build.getUrl());
            return new ResultMessage(true, "构建成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(true, "构建失败", null);
        }

    }

    @Override
    public JobInformationVO getJob(String name) {
        JobWithDetails job = null;
        JobInformationVO jobInformationVO = null;
        List<BuildVO> buildVOS = new ArrayList<>();

        try {
            int health = 0;
            job = jenkinsServer.getJob(name);

            List<Build> buildList = job.getAllBuilds();
            System.out.println();
            System.out.println();
            System.out.println(buildList.size());
            System.out.println();
            System.out.println();
            for (Build build : buildList) {
                BuildVO buildVO = new BuildVO(build.getNumber(), build.getUrl());
                buildVOS.add(buildVO);
            }
            if (buildVOS.size() <= 0) {
                health = 0;
            } else {
                for (int i = buildList.size() - 5; i < buildList.size(); i++) {
                    if (i >= 0 && buildList.get(i).details().getResult().toString().equals("SUCCESS")) {
                        health++;
                    }
                }
            }
            BuildVO lastBuild = new BuildVO(job.getLastBuild().getNumber(), job.getLastBuild().getUrl());
            BuildVO lastFailBuild = new BuildVO(job.getLastFailedBuild().getNumber(), job.getLastFailedBuild().getUrl());
            BuildVO lastSuccessfulBuild = new BuildVO(job.getLastSuccessfulBuild().getNumber(), job.getLastSuccessfulBuild().getUrl());
            BuildVO lastStablefulBuild = new BuildVO(job.getLastStableBuild().getNumber(), job.getLastStableBuild().getUrl());
            //jobInformationVO = new JobInformationVO(job.getName(), job.getUrl(), buildVOS, "构建稳定性:过去5次构建中" + (5 - health) + "次失败", lastBuild, lastFailBuild, lastSuccessfulBuild, lastStablefulBuild, job.getDescription());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobInformationVO;
    }

    @Override
    public ResultMessage updateJob(String name, String description, String jenkinsfilePath) throws IOException {
        if (jenkinsServer.getJob(name) == null) {
            return new ResultMessage(false, "不存在名为" + name + "的项目", null);
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true);
        try {
            // 从XML文档中获取DOM文档实例
            DocumentBuilder db = dbf.newDocumentBuilder();
            // 获取Document对象
            Document xmldoc = db.parse("/var/lib/jenkins/jobs/" + name + "/config.xml");

            // 获取根节点
            Element root = xmldoc.getDocumentElement();
            // 定位id为001的节点

            // 将age节点的内容更改为28
            root.getElementsByTagName("description").item(0).setTextContent(description);

            //root.getElementsByTagName("name").item(0).setTextContent(branch);
            root.getElementsByTagName("scriptPath").item(0).setTextContent(jenkinsfilePath);

            // 保存
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer former = factory.newTransformer();
            former.transform(new DOMSource(xmldoc), new StreamResult(new File("/home/xujianghe/jenkins/config/config-" + name + "-.xml")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder build = new StringBuilder();
        try {
            InputStream in = new FileInputStream("/home/xujianghe/jenkins/config/config-" + name + "-.xml");
            InputStreamReader read = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                build.append(lineTxt);
            }

            //System.out.println(build.toString());
            jenkinsServer.updateJob(name, build.toString());
            return new ResultMessage(true, "更新成功", getJob(name));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(false, "更新失败", null);
        }

    }

    @Override
    public ResultMessage periodJob(String name, int period) throws IOException {
        Job job = jenkinsServer.getJob(name);
        if (job == null) {
            return new ResultMessage(false, "未找到名为" + name + "的项目", null);
        }
        if (period <= 0 || period >= 24) {
            return new ResultMessage(false, "时间间隔请保持在0-23小时", null);
        }
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true);
        try {
            // 从XML文档中获取DOM文档实例
            DocumentBuilder db = dbf.newDocumentBuilder();
            // 获取Document对象
            Document xmldoc = db.parse("/var/lib/jenkins/jobs/" + name + "/config.xml");

            // 获取根节点
            Element root = xmldoc.getDocumentElement();
            // 定位id为001的节点

            // 将age节点的内容更改为28
            root.getElementsByTagName("interval").item(0).setTextContent(String.valueOf(3600000 * period));


            // 保存
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer former = factory.newTransformer();
            former.transform(new DOMSource(xmldoc), new StreamResult(new File("/home/xujianghe/jenkins/config/config-" + name + "-.xml")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        StringBuilder build = new StringBuilder();
        try {
            InputStream in = new FileInputStream("/home/xujianghe/jenkins/config/config-" + name + "-.xml");
            InputStreamReader read = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                build.append(lineTxt);
            }
            //String jobname = "oopsa";
            //System.out.println(build.toString());
            jenkinsServer.updateJob(name, build.toString());

            return new ResultMessage(true, "触发周期更新成功", period);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(false, "触发周期更新失败", null);
        }
    }

    @Override
    public ResultMessage deleteJob(String name) throws IOException {
        Job job = jenkinsServer.getJob(name);
        if (job == null) {
            return new ResultMessage(false, "未找到名为" + name + "的项目", null);
        }
        try {
            jenkinsServer.deleteJob(name);
            return new ResultMessage(true, "已删除名为" + name + "的项目", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResultMessage(false, "删除失败", null);
    }

    @Override
    public BuildInformationVO getBuild(String name, String number) throws IOException {
        BuildWithDetails build = jenkinsServer.getJob(name).getBuildByNumber(Integer.valueOf(number)).details();

        return new BuildInformationVO(build.isBuilding(), build.getDescription(), build.getDisplayName(), build.getDuration(), build.getEstimatedDuration(), build.getFullDisplayName(), build.getId(), build.getNumber(), build.getQueueId(), build.getResult().toString(), build.getTimestamp(), build.getUrl());
    }


    /*public List<ComputerVO> getComputers() throws IOException {
        Map<String, com.offbytwo.jenkins.model.Computer> maps = jenkinsServer.getComputers();
        Collection<com.offbytwo.jenkins.model.Computer> computers = maps.values();
        List<ComputerVO> result = new ArrayList<>();
        for (Computer computer : computers) {

            ComputerVO vo = new ComputerVO(computer.getDisplayName(), computer.details().getJnlp(), computer.details().getNumExecutors(), computer.details().getOffline(), computer.details().getOfflineCauseReason());
            result.add(vo);
        }
        return result;
    }*/

    @Override
    public ResultMessage getJobPeriod(String name) throws IOException {
        Job job = jenkinsServer.getJob(name);
        if (job == null) {
            return new ResultMessage(false, "未找到名为" + name + "的项目", -1);
        }
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true);
        // 从XML文档中获取DOM文档实例
        DocumentBuilder db = null;
        String result = "";
        try {
            db = dbf.newDocumentBuilder();
            Document xmldoc = db.parse("/var/lib/jenkins/jobs/" + name + "/config.xml");
            Element root = xmldoc.getDocumentElement();
            result = root.getElementsByTagName("interval").item(0).getTextContent();
            //System.out.println();
            //System.out.println();
            System.out.println(result);
            //System.out.println();
            //System.out.println();
            return new ResultMessage(true, "", Integer.parseInt(result) / 3600000);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return new ResultMessage(false, "获取周期失败", -1);
        } catch (SAXException e) {
            e.printStackTrace();
            return new ResultMessage(false, "获取周期失败", -1);
        }
    }


    @Override
    public ResultMessage createCredentials(String name, String SSHPrivateKey) throws URISyntaxException {
        JenkinsHttpClient client = new JenkinsHttpClient(new URI(jenkinsURL.getJenkins()), jenkinsURL.getName(), jenkinsURL.getPassword());
        String json = "json={\"\":\"0\",\"credentials\":{\"scope\":\"GLOBAL\",\"id\":\"" + name + "\",\"username\":\"" + name + "\",\"password\":\"\",\"privateKeySource\":{\"stapler-class\":\"com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey$DirectEntryPrivateKeySource\",\"privateKey\":\"" + SSHPrivateKey + "\",},\"description\":\"\",\"stapler-class\":\"com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey\"}}";

        String result = "";
        //client.post_text("credentials/store/system/domain/_/createCredentials",req, ContentType.APPLICATION_FORM_URLENCODED,true);
        try {
            result = client.post_text("credentials/store/system/domain/_/createCredentials", json, ContentType.APPLICATION_FORM_URLENCODED, false);
            return new ResultMessage(true, "创建认证成功！", result);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultMessage(false, "创建认证失败！", result);
        }


    }

    @Override
    public ResultMessage uploadFileToGitLab(String groupName, String projectName, String projectId, String filePath, String branch, String commit_message) {
        String CI_PROJECT_NAMESPACE = "$CI_PROJECT_NAMESPACE";
        String CI_PROJECT_NAME = "$CI_PROJECT_NAME";
        String CI_PROJECT_ID = "$CI_PROJECT_ID";
        File file = new File("/home/xujianghe/jenkins/Jenkinsfile");
        File newFile = new File("/home/xujianghe/jenkins/config/Jenkinsfile-" + projectId);
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            CharArrayWriter tempStream = new CharArrayWriter();

            // 替换
            String line = null;

            while ((line = br.readLine()) != null) {
                // 替换每行中, 符合条件的字符串
                line = line.replaceAll(CI_PROJECT_NAME, projectName);
                line = line.replaceAll(CI_PROJECT_NAMESPACE, groupName);
                line = line.replaceAll(CI_PROJECT_ID, projectId);
                // 将该行写入内存
                tempStream.write(line);
                // 添加换行符
                tempStream.append(System.getProperty("line.separator"));
            }

            // 关闭 输入流
            br.close();

            // 将内存中的流 写入 文件
            FileWriter out = new FileWriter(newFile);
            tempStream.writeTo(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ResultMessage(false, "上传Jenkinsfile失败", null);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultMessage(false, "上传Jenkinsfile失败", null);
        }

        StringBuilder build = new StringBuilder();
        try {
            InputStream in = new FileInputStream("/home/xujianghe/jenkins/config/Jenkinsfile-" + projectId);
            InputStreamReader read = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                build.append(lineTxt);
            }


        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(false, "上传Jenkinsfile失败", null);
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("content", build.toString());
        map.put("commit_message", commit_message);
        map.put("branch", branch);
        String result = gitLabFeign.upload(projectId, filePath, map);
        return new ResultMessage(true, "上传Jenkinsfile成功", result);
    }


}

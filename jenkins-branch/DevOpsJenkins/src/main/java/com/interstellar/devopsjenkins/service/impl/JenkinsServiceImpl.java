package com.interstellar.devopsjenkins.service.impl;


import com.interstellar.devopsjenkins.service.JenkinsService;
import com.interstellar.devopsjenkins.util.ResultMessage;
import com.interstellar.devopsjenkins.vo.BuildInformationVO;
import com.interstellar.devopsjenkins.vo.BuildVO;
import com.interstellar.devopsjenkins.vo.JobInformationVO;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Build;
import com.offbytwo.jenkins.model.BuildWithDetails;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class JenkinsServiceImpl implements JenkinsService {
    @Autowired
    JenkinsServer jenkinsServer;

    public static Node selectSingleNode(String express, Element source) {
        Node result = null;
        //创建XPath工厂
        XPathFactory xpathFactory = XPathFactory.newInstance();
        //创建XPath对象
        XPath xpath = xpathFactory.newXPath();
        try {
            result = (Node) xpath.evaluate(express, source, XPathConstants.NODE);
            System.out.println(result);
        } catch (XPathExpressionException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public ResultMessage createJob(String name, String description, String url, String branch, String jenkinsfilePath) throws IOException {

        if (jenkinsServer.getJob(name) != null) {
            return new ResultMessage(false, "已存在名为" + name + "的项目", null);
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true);
        try {
            // 从XML文档中获取DOM文档实例
            DocumentBuilder db = dbf.newDocumentBuilder();
            // 获取Document对象
            Document xmldoc = db.parse("C:/Jenkins/jobs/config.xml");

            // 获取根节点
            Element root = xmldoc.getDocumentElement();
            // 定位id为001的节点

            // 将age节点的内容更改为28
            root.getElementsByTagName("description").item(0).setTextContent(description);

            root.getElementsByTagName("name").item(0).setTextContent(branch);
            root.getElementsByTagName("scriptPath").item(0).setTextContent(jenkinsfilePath);


            root.getElementsByTagName("url").item(0).setTextContent(url);
            // 保存
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer former = factory.newTransformer();
            former.transform(new DOMSource(xmldoc), new StreamResult(new File("C:/Jenkins/jobs/config-" + name + "-.xml")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder build = new StringBuilder();
        try {
            InputStream in = new FileInputStream("C:/Jenkins/jobs/config-" + name + "-.xml");
            InputStreamReader read = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                build.append(lineTxt);
            }
            //String jobname = "oopsa";
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(build.toString());
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            jenkinsServer.createJob(name, build.toString());
            return new ResultMessage(true, "创建成功", getJob(name));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(true, "创建失败", null);
        }

    }

    @Override
    public ResultMessage buildJob(String name) {

        try {
            Job job = jenkinsServer.getJob(name);
            job.build();
            JobWithDetails jobWithDetails = job.details();
            Build build = jobWithDetails.getLastBuild();

            BuildVO vo = new BuildVO(build.getNumber(), build.getUrl());
            return new ResultMessage(true, "构建成功", vo);
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
            jobInformationVO = new JobInformationVO(job.getName(), job.getUrl(), buildVOS, "构建稳定性:过去5次构建中" + (5 - health) + "次失败", lastBuild, lastFailBuild, lastSuccessfulBuild, lastStablefulBuild, job.getDescription());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobInformationVO;
    }

    @Override
    public ResultMessage updateJob(String name, String description, String url, String branch, String jenkinsfilePath) throws IOException {
        if (jenkinsServer.getJob(name) == null) {
            return new ResultMessage(false, "不存在名为" + name + "的项目", null);
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true);
        try {
            // 从XML文档中获取DOM文档实例
            DocumentBuilder db = dbf.newDocumentBuilder();
            // 获取Document对象
            Document xmldoc = db.parse("C:/Jenkins/jobs/" + name + "/config.xml");

            // 获取根节点
            Element root = xmldoc.getDocumentElement();
            // 定位id为001的节点

            // 将age节点的内容更改为28
            root.getElementsByTagName("description").item(0).setTextContent(description);

            root.getElementsByTagName("name").item(0).setTextContent(branch);
            root.getElementsByTagName("scriptPath").item(0).setTextContent(jenkinsfilePath);


            root.getElementsByTagName("url").item(0).setTextContent(url);
            // 保存
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer former = factory.newTransformer();
            former.transform(new DOMSource(xmldoc), new StreamResult(new File("C:/Jenkins/jobs/config-" + name + "-.xml")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder build = new StringBuilder();
        try {
            InputStream in = new FileInputStream("C:/Jenkins/jobs/config-" + name + "-.xml");
            InputStreamReader read = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                build.append(lineTxt);
            }

            System.out.println(build.toString());
            jenkinsServer.updateJob(name, build.toString());
            return new ResultMessage(true, "更新成功", getJob(name));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(true, "更新成功", null);
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
            Document xmldoc = db.parse("C:/Jenkins/jobs/" + name + "/config.xml");

            // 获取根节点
            Element root = xmldoc.getDocumentElement();
            // 定位id为001的节点

            // 将age节点的内容更改为28
            root.getElementsByTagName("spec").item(0).setTextContent("H 0-23/" + period + " * * *");


            // 保存
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer former = factory.newTransformer();
            former.transform(new DOMSource(xmldoc), new StreamResult(new File("C:/Jenkins/jobs/config-" + name + "-.xml")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        StringBuilder build = new StringBuilder();
        try {
            InputStream in = new FileInputStream("C:/Jenkins/jobs/config-" + name + "-.xml");
            InputStreamReader read = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                build.append(lineTxt);
            }
            //String jobname = "oopsa";
            System.out.println(build.toString());
            jenkinsServer.updateJob(name, build.toString());
            return new ResultMessage(true, "触发周期更新成功", period);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(true, "触发周期更新失败", null);
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


}

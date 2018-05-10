package com.interstellar.devopsjenkins.service;

import com.interstellar.devopsjenkins.util.ResultMessage;
import com.interstellar.devopsjenkins.vo.BuildInformationVO;
import com.interstellar.devopsjenkins.vo.ComputerVO;
import com.interstellar.devopsjenkins.vo.JobInformationVO;

import java.io.IOException;
import java.util.List;

public interface JenkinsService {
    /**
     * 创建项目，应该在创建团队项目时自动创建
     *
     * @param name            项目名称（流水线名）
     * @param description     描述
     * @param url             地址，gitlab仓库所在地址
     * @param branch          分支
     * @param jenkinsfilePath 脚本文件相对路径
     * @return 是否成功
     * @throws IOException
     */
    ResultMessage createJob(String name, String description, String url, String branch, String jenkinsfilePath) throws IOException;

    ResultMessage buildJob(String name) throws IOException;


    /**
     * 获取项目详细信息
     *
     * @param name 项目名称
     * @return
     */
    JobInformationVO getJob(String name);

    /**
     * 更新项目描述、仓库地址、分支和脚本文件相对地址
     *
     * @param name
     * @param description
     * @param url
     * @param branch
     * @param jenkinsfilePath
     * @return
     */


    ResultMessage updateJob(String name, String description, String url, String branch, String jenkinsfilePath) throws IOException;

    /**
     * 设置周期性触发构建的间隔（小时）
     *
     * @param name
     * @param period 1-23的整数
     * @return
     * @throws IOException
     */
    ResultMessage periodJob(String name, int period) throws IOException;

    ResultMessage deleteJob(String name) throws IOException;

    BuildInformationVO getBuild(String name,String number)throws IOException;

    List<ComputerVO> getComputers() throws IOException;

    ResultMessage initJenkinsJob(String groupName,String projectName,String name, String description) throws IOException;
}

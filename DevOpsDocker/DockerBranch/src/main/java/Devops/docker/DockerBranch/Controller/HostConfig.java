package Devops.docker.DockerBranch.Controller;

import Devops.docker.DockerBranch.Entity.Basicimage;
import Devops.docker.DockerBranch.Entity.Container;
import Devops.docker.DockerBranch.Service.Test;
import Devops.docker.DockerBranch.dao.testLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HostConfig {

    @Autowired
    Test test;

    /**
     *
     * @param hostname 主机名
     * @param opsSystem 操作系统类型，1为centos,2为ubuntu
     * @param ip 主机ip
     * @param root root账号
     * @param passwd root密码
     * @param autoInstall 是否自动安装。true为需要系统安装，false为用户自行安装
     * @param username 用户名
     * @param projectId 项目id
     * @return
     * 1：连接失败，未能找到服务器
     * 2：登录失败，账号或密码错误
     * 3：docker安装失败
     * 4. 配置完成
     */
    @RequestMapping(value = "/configHost" ,method = RequestMethod.POST)
    public int hostConfig(@RequestParam String hostname,
                          @RequestParam int opsSystem,
                          @RequestParam String ip,
                          @RequestParam String root,
                          @RequestParam String passwd,
                          @RequestParam(value="auto" ,defaultValue = "true") String autoInstall,
                          @RequestParam String username,
                          @RequestParam String projectId){

        Container image = new Container();

        image.setContainerId(2);
        image.setCreator("123");
        image.setDate("12:12:12");
        image.setImage("tomcat");
        image.setPath("/user/sta");
        image.setPort("8080");
        image.setTaskId(1);
        test.save(image);
        return 1;
    }

}

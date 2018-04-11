package Devops.docker.DockerBranch.Controller;

import Devops.docker.DockerBranch.Entity.Basicimage;
import Devops.docker.DockerBranch.Entity.Container;
import Devops.docker.DockerBranch.Entity.Host;
import Devops.docker.DockerBranch.Service.HostService;
import Devops.docker.DockerBranch.Service.Test;
import Devops.docker.DockerBranch.Service.tools.DateTool;
import Devops.docker.DockerBranch.dao.testLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HostConfig {

    @Autowired
    HostService hostService;

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
    @RequestMapping(value = "/addHost" ,method = RequestMethod.POST)
    public int addHost(@RequestParam String hostname,
                          @RequestParam int opsSystem,
                          @RequestParam String ip,
                          @RequestParam String root,
                          @RequestParam String passwd,
                          @RequestParam(value="auto" ,defaultValue = "true") String autoInstall,
                          @RequestParam String username,
                          @RequestParam String projectId){

        Host host = new Host();
        host.setHostname(hostname);
        if(opsSystem==1){
            host.setOpsSystem("centos");
        }else{
            host.setOpsSystem("ubuntu");
        }
        host.setIp(ip);
        host.setCreator(username);
        host.setPassword(passwd);
        host.setProjectid(projectId);
        host.setRoot(root);
        host.setAuto_installed(autoInstall);
        host.setHostname(hostname);
        host.setDate(DateTool.getTimeNow());

        int result = hostService.addHost(host);
        return result;
    }

    /**
     *
     * 考虑到主机ip和操作系统一般不会变，就不提供这方面修改了，一般就是改一下hostname,root账号，密码和是否自动安装
     *
     * @param hostId 从getHosts可获得当前正在配置的主机id
     * @param hostname 主机名
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
    @RequestMapping(value = "/configHost",method = RequestMethod.POST)
    public int configHost(@RequestParam String hostId,
                          @RequestParam String hostname,
                          @RequestParam String root,
                          @RequestParam String passwd,
                          @RequestParam(value="auto" ,defaultValue = "true") String autoInstall,
                          @RequestParam String username,
                          @RequestParam String projectId){
        Host host = new Host();
        host.setHostname(hostname);
        host.setHostId(Integer.parseInt(hostId));
        host.setRoot(root);
        host.setPassword(passwd);
        host.setProjectid(projectId);
        host.setAuto_installed(autoInstall);
        host.setCreator(username);

        return hostService.configHost(host);
    }

    /**
     *
     * @param hostId 被删除的主机id
     */
    @RequestMapping(value = "/deleteHost",method = RequestMethod.POST)
    public void deleteHost(@RequestParam String hostId){
        hostService.deleteHost(hostId);
    }

    @RequestMapping(value = "/testHost",method = RequestMethod.GET)
    public int testHost(@RequestParam String hostId){
        return hostService.testHost(hostId);
    }
}

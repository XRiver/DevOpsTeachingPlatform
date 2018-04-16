package Devops.docker.DockerBranch.Controller;

import Devops.docker.DockerBranch.Entity.Basicimage;
import Devops.docker.DockerBranch.Entity.Container;
import Devops.docker.DockerBranch.Entity.Host;
import Devops.docker.DockerBranch.Service.HostService;
import Devops.docker.DockerBranch.Service.Test;
import Devops.docker.DockerBranch.Service.tools.DateTool;
import Devops.docker.DockerBranch.dao.testLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HostConfig {

    @Autowired
    HostService hostService;

    /**
     *
     * @param host 添加的主机
     * @return
     * 1：连接失败，未能找到服务器
     * 2：登录失败，账号或密码错误
     * 3：docker安装失败
     * 4. 配置完成
     */
    @RequestMapping(value = "/addHost" ,method = RequestMethod.POST)
    public int addHost(@RequestBody Host host){

        host.setDate(DateTool.getTimeNow());
        host.setHostId(0);
        int result = hostService.addHost(host);
        return result;
    }

    /**
     *
     * 考虑到主机ip和操作系统一般不会变，就不提供这方面修改了，一般就是改一下hostname,root账号，密码和是否自动安装
     *
     * @param host
     * @return
     * 1：连接失败，未能找到服务器
     * 2：登录失败，账号或密码错误
     * 3：docker安装失败
     * 4. 配置完成
     */
    @RequestMapping(value = "/configHost",method = RequestMethod.POST)
    public int configHost(@RequestBody Host host){

        return hostService.configHost(host);
    }

    /**
     *
     * @param hostId 被删除的主机id
     */
    @RequestMapping(value = "/deleteHost",method = RequestMethod.GET)
    public void deleteHost(@RequestParam String hostId){
        hostService.deleteHost(hostId);
    }

    @RequestMapping(value = "/testHost",method = RequestMethod.GET)
    public int testHost(@RequestParam String hostId){
        return hostService.testHost(hostId);
    }
}

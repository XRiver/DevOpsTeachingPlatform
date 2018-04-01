package Devops.docker.DockerBranch.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Console;

@RestController
public class HostConfig {

    /**
     *
     * @param hostname 主机名
     * @param opsSystem 操作系统类型，1为centos,2为ubuntu
     * @param ip 主机ip
     * @param root root账号
     * @param passwd root密码
     * @param autoInstall 是否自动安装。true为需要系统安装，false为用户自行安装
     * @param userId 用户id
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
                          @RequestParam String userId,
                          @RequestParam String projectId){
        return 4;
    }

}

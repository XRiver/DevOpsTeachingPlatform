package Devops.docker.DockerBranch.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WsController {
    @RequestMapping("sockettest")
    public String sockettest(){
        return "sockettest";
    }

    @RequestMapping("tongji")
    public String tongji(Model model){
        model.addAttribute("num", SocketServer.getOnlineNum());
        model.addAttribute("users", SocketServer.getOnlineUsers());
        return "tongji";
    }

    @RequestMapping("sendmsg")
    @ResponseBody
    public String sendmsg(@RequestParam String username,@RequestParam String msg){
//        String username = request.getParameter("username");
//        String msg = request.getParameter("msg");
        SocketServer.sendMessage(msg, username);
        return "success";
    }


}

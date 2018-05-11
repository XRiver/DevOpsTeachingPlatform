package edu.nju.controller;

import edu.nju.model.SSHKey;
import edu.nju.model.User;
import edu.nju.service.SSHKeyService;
import edu.nju.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2018/3/30.
 */
@Controller
public class TestController2 {
    @Autowired
    UserService userService;
    @Autowired
    SSHKeyService sshKeyService;

    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    public String welcome(){
        return "index";
    }

    @RequestMapping(value = "/alluser",method = RequestMethod.GET)
    public String allusers(Model model){
        List<User> list=userService.getAllUsers();
        model.addAttribute("list",list);
        return "list";
        //return userService.getAllUsers().toString();
    }

    @RequestMapping(value = "/input",method = {RequestMethod.POST,RequestMethod.GET})
    public String input(){
        return "input";
    }

    @RequestMapping(value = "/allsshkey",method = RequestMethod.GET)
    public String allsshkey(Model model){
        List<SSHKey> list=sshKeyService.getAllSSHkey();
        model.addAttribute("list",list);
        return "sshkeylist";
        //return userService.getAllUsers().toString();
    }

    @RequestMapping(value = "/newsshkey",method = RequestMethod.POST)
    public String newsshkey(HttpServletRequest request, Model model){
        String title=request.getParameter("title");
        String key=request.getParameter("key");
        String userID=request.getParameter("id");
        SSHKey sshKey=new SSHKey();
        sshKey.setKey(key);
        sshKey.setTitle(title);
        sshKeyService.addSSHKey(userID,sshKey);

        return "redirect:/allsshkey";
    }
}

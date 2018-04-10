package edu.nju.controller;

import com.alibaba.fastjson.JSON;
import edu.nju.config.LogBean;
import edu.nju.model.SSHKey;
import edu.nju.model.User;
import edu.nju.service.SSHKeyService;
import edu.nju.service.TransferService;
import edu.nju.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/1.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TransferService transferService;

    @Autowired
    SSHKeyService sshKeyService;

//    @RequestMapping(value = "/new/{username}/{name}/{password}/{email}",method = {RequestMethod.GET,RequestMethod.POST})
//    public String newUser(@PathVariable("username") String username,@PathVariable("name") String name,
//                          @PathVariable("password") String password,@PathVariable("email") String email){
//        User user=new User();
//        user.setPassword(password);
//        user.setUsername(username);
//        user.setName(name);
//        user.setEmail(email);
//        String result=userService.addUser(user);
//        return result;
//    }

    @RequestMapping(value = "/user/newuser",method = RequestMethod.POST)
    public String newUser2(@RequestBody Map<String,String> map){
        String id=map.get("id");
        String name=map.get("name");
        String username=map.get("username");
        String password=map.get("password");
        String email=map.get("email");
        User user=new User();
        user.setPassword(password);
        user.setUsername(username);
        user.setEmail(email);
        user.setName(name);
        user.setId(Integer.parseInt(id));
        String result=userService.addUser(user);
        if(result==null){
            LogBean.log("usercontroller adduser: return null");
            return null;
        }else{
            String gitlabID= JSON.parseObject(result,User.class).getId()+"";
            transferService.insertUserID(id,gitlabID);
            String gitlabIDt=transferService.getGitlabUserIDByUserID(id);
            LogBean.log("插入gitlabID ："+gitlabIDt);
        }
        //String gitlabID=transferService.getGitlabUserIDByUserID(id);
        return JSON.toJSONString(user);
    }

    @RequestMapping(value = "/user/allusers",method = RequestMethod.GET)
    public String getAllUsers(){
        List<User> userList=userService.getAllUsers();
        return JSON.toJSONString(userList);
    }

    @RequestMapping(value = "/sshkey/{userid}",method = RequestMethod.GET)
    public String getAllSSHkeys(@PathVariable String userid){
        String gitlabID=transferService.getGitlabUserIDByUserID(userid);
        List<SSHKey> sshKeys=sshKeyService.getUserSSHKey(gitlabID);
        return JSON.toJSONString(sshKeys);
    }

    @RequestMapping(value = "/user/deleteuser/{userid}",method = RequestMethod.DELETE)
    public String deleteUser(){
        //TODO
        return "";
    }

    @RequestMapping(value = "/sshkey/newsshkey" ,method = RequestMethod.POST)
    public String addSSHKey(@RequestBody Map<String,String> map){
        String id= map.get("id");
        String title=map.get("title");
        String key=map.get("key");
        SSHKey sshKey=new SSHKey();
        sshKey.setTitle(title);
        sshKey.setKey(key);

        String gitlabID=transferService.getGitlabUserIDByUserID(id);

        String result=sshKeyService.addSSHKey(gitlabID,sshKey);
        LogBean.log("增加SSHKEY ： "+result);
        return result;
    }

    @RequestMapping(value = "/sshkey/deletekey/{userid}/{keyid}",method = RequestMethod.DELETE)
    public String deleteSSHKey(){
        //TODO
        return "";
    }
}

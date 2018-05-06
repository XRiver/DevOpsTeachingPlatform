package edu.nju.controller;

import edu.nju.api.Util;
import edu.nju.config.LogBean;
import edu.nju.model.SSHKey;
import edu.nju.model.User;
import edu.nju.service.SSHKeyService;
import edu.nju.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2018/3/30.
 */
@RestController
public class TestController {

    @Autowired
    UserService userService;
    @Autowired
    SSHKeyService sshKeyService;

    //同名问题
    @RequestMapping(value = "/newuser",method = RequestMethod.GET)
    public String newuser(){
        User user=new User();
        user.setEmail("54277272@qq.com");
        user.setName("testor1");
        user.setUsername("testor1");
        user.setPassword("yhq960726");

        User user2=new User();
        user2.setEmail("542772723@qq.com");
        user2.setName("testor3");
        user2.setUsername("testor3");
        user2.setPassword("yhq960726");
        userService.addUser(user2);
        return "new user added!!";
    }


    @RequestMapping(value = "testlog",method = RequestMethod.GET)
    public String test1(){
        LogBean.log("第一个测试");
        return "第一个测试";
    }


    @RequestMapping(value = "testlog2",method = RequestMethod.GET)
    public String test2(){
        LogBean.log("第2个测试");
        return "第2个测试";
    }

}

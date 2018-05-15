package teamworkbranch.module.user.web;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import teamworkbranch.exception.ExistedException;
import teamworkbranch.exception.InvalidUserNameException;
import teamworkbranch.exception.NonprivilegedUserException;
import teamworkbranch.exception.PasswordErrorException;
import teamworkbranch.module.project.model.Project;
import teamworkbranch.module.project.service.PManagerService;
import teamworkbranch.module.project.service.ProjectService;
import teamworkbranch.module.user.model.User;
import teamworkbranch.module.user.service.IdentificationService;
import teamworkbranch.module.user.service.UserService;
import teamworkbranch.module.user.vo.LoginInfo;

import java.util.List;

/**
 * Created by liying on 2018/4/17.
 */
@RestController
@RequestMapping("/identification")
public class IdentificationController {


    @Autowired
    IdentificationService identificationService;

    @RequestMapping(value = "/logIn", method = RequestMethod.POST)
    @ResponseBody
    public String login(String username, String password) {
        LoginInfo li = new LoginInfo();
        String changedPwd = DigestUtils.md5DigestAsHex((username + password).getBytes());
        JSONObject toReturn =new JSONObject();

        li.setName(username);
        li.setPassword(changedPwd);
        try {
            identificationService.logIn(li);
            toReturn.put("success",true);
            toReturn.put("msg","success");

        } catch (PasswordErrorException e) {
            toReturn.put("success",false);
            toReturn.put("msg",e.getMessage());
        } catch (InvalidUserNameException e) {
            toReturn.put("success",false);
            toReturn.put("msg",e.getMessage());
        }

        return toReturn.toString();
    }


    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    @ResponseBody
    public String signUp(String username,String password,Integer role,String email,String name,String userId) {
        JSONObject toReturn = new JSONObject();
        String changedPwd=DigestUtils.md5DigestAsHex((username + password).getBytes());
        User user=new User(username,changedPwd,name,userId,email,role);
        try {
            identificationService.signUp(user);
            toReturn.put("success",true);
            toReturn.put("msg","success");

        }catch (Exception e) {
            toReturn.put("success",false);
            toReturn.put("msg",e.getMessage());
        }

        return toReturn.toString();
    }







}

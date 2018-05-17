package teamworkbranch.module.user.web;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import teamworkbranch.exception.InvalidUserNameException;
import teamworkbranch.exception.PasswordErrorException;
import teamworkbranch.module.user.model.User;
import teamworkbranch.module.user.service.IdentificationService;
import teamworkbranch.module.user.service.UserService;
import teamworkbranch.module.user.vo.LoginInfo;
import teamworkbranch.module.user.vo.UserVO;

/**
 * Created by liying on 2018/4/17.
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;
    @Autowired
    IdentificationService identificationService;


    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public String modify(String username, String email,String name,String userId) {
        JSONObject toReturn =new JSONObject();
        try {
            User user=userService.getUserInfo(username);
            user.setName(name);
            user.setEmail(email);
            user.setUserId(userId);
            userService.editInfo(user);
            toReturn.put("success",true);
            toReturn.put("msg","success");
        } catch (InvalidUserNameException e) {
            toReturn.put("success",false);
            toReturn.put("msg",e.getMessage());
        }

        return toReturn.toString();
    }


    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    @ResponseBody
    public UserVO getInfo(String username) {
        User user=null;
        UserVO userVO=new UserVO();

        try {
            user=userService.getUserInfo(username);
            userVO.setName(user.getName());
            userVO.setUserId(user.getUserId());
            userVO.setUsername(user.getUsername());
            userVO.setEmail(user.getEmail());
            userVO.setRole(user.getRole());
            userVO.setCreateTime(user.getCreateTime());
            userVO.setUpdateTime(user.getUpdateTime());

        } catch (InvalidUserNameException e) {
            return userVO;
        }
        return userVO;
    }


    @RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
    @ResponseBody
    public String modify(String username, String newPassword,String oldPassword) {
        JSONObject toReturn =new JSONObject();
        LoginInfo li=new LoginInfo();
        String changedPwd = DigestUtils.md5DigestAsHex((username + oldPassword).getBytes());
        li.setName(username);
        li.setPassword(changedPwd);
        try {
            identificationService.logIn(li);
            User user=userService.getUserInfo(username);
            String changedNewPwd=DigestUtils.md5DigestAsHex((username + newPassword).getBytes());
            user.setPassword(changedNewPwd);
            userService.editInfo(user);
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



    




}

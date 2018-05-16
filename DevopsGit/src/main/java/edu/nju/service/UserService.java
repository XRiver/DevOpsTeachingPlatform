package edu.nju.service;

import com.alibaba.fastjson.JSON;
import edu.nju.api.Util;
import edu.nju.config.LogBean;
import edu.nju.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/25.
 */
@Service
public class UserService {

    /**
     *
     * @param user
     * @return  1成功   0失败
     */
    public String  addUser(User user){
        String username=user.getUsername();
        String name=user.getName();
        String password=user.getPassword();
        String email=user.getEmail();
        Map<String,String> paramMap=new HashMap<String,String>();
        paramMap.put("email",email);
        paramMap.put("password",password);
        paramMap.put("username",username);
        paramMap.put("name",name);
        paramMap.put("skip_confirmation",true+"");
        String result=Util.post("/users",paramMap);
        result=result.trim();
        LogBean.log("add user result: "+result);
        return result;

    }

    public List<User> getAllUsers(){
        String userliststr=Util.get("/users",null);
        List<User> userList = JSON.parseArray(userliststr,User.class);
        return userList;
    }

    public User getUser(String userID){
        String userresult= Util.get("/users/"+userID,null);
        User user= JSON.parseObject(userresult,User.class);
        return  user;
    }

    //TODO
    //修改，删除用户



}

package edu.nju.controller;

import com.alibaba.fastjson.JSON;
import edu.nju.config.LogBean;
import edu.nju.model.Group;
import edu.nju.service.GroupService;
import edu.nju.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Administrator on 2018/4/9.
 */
@RestController
public class GroupController {

    @Autowired
    TransferService transferService;

    @Autowired
    GroupService groupService;

    @RequestMapping(value = "/group/newgroup" ,method = RequestMethod.POST)
    public String newGroup(@RequestBody Map<String,String> map) {
        String id = map.get("id");
        String name = map.get("id");
        String description = map.get("id");
        String visibility = map.get("id");

        String result=groupService.addGroup(name,name,description,visibility);

        LogBean.log("增加group result："+result);
        if(result==null){
            LogBean.log("增加group shibai  null");
        }else {
            String gitlabID= JSON.parseObject(result,Group.class).getId();
            transferService.insertGroupID(id,gitlabID);

        }

        return "";
    }
}

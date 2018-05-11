package edu.nju.service;

import com.alibaba.fastjson.JSON;
import edu.nju.api.Util;
import edu.nju.model.GroupMember;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yhq on 2018/3/25.
 */
@Service
public class GroupService {

    /**
     *  id
     * @param name  required
     * @param path  =name
     * @param description
     * @param visibility Can be private, internal, or public.
     * @return
     */
    public String addGroup(String name,String path,String description,String visibility){
        Map<String,String> map=new HashMap<>();
        map.put("name",name);
        map.put("path",path);
        map.put("description",description);
        map.put("visibility",visibility);
        String result= Util.post("/groups",map);
        return result;
    }

    public List<GroupMember> getMembersOfgroup(String groupID){
        String str=Util.get("/groups/"+groupID+"/members",null);
        List<GroupMember> groupMemberList= JSON.parseArray(str,GroupMember.class);
        return groupMemberList;
    }

    public String addMemberToGroup(String groupID,String userID){
        Map<String,String> map=new HashMap<>();
        map.put("id",groupID);
        map.put("user_id",userID);
        map.put("access_level",50+"");
        String result=Util.post("/groups/"+groupID+"/members",map);
        return result;
    }

    /**
     * TODO
     * delete 方法未实现
     * @param groupID
     * @param userID
     * @return
     */
    public String removeMemberFromGroup(String groupID,String userID){
        Map<String,String> map=new HashMap<>();
        map.put("id",groupID);
        map.put("user_id",userID);
        //String result=Util.delete("/groups/"+groupID+"/members",map);
        return "";
    }

}

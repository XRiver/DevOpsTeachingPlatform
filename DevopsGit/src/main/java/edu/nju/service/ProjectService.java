package edu.nju.service;

import edu.nju.api.Util;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yhq on 2018/4/1.
 */
@Service
public class ProjectService {

    public String createProject(String name){
        Map<String,String> map=new HashMap<>();
        map.put("name",name);
        map.put("path",name);
        String result= Util.post("/projects",map);
        return result;
    }

    public String transferPro2Group(String groupID,String projectID){
        Map<String,String> map=new HashMap<>();
        map.put("id",groupID);
        map.put("project_id",projectID);
        String result=Util.post("/groups/"+groupID+"/projects/"+projectID,map);
        return result;
    }
}

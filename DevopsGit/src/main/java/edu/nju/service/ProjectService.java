package edu.nju.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import edu.nju.api.Util;
import edu.nju.model.Project;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;

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

    public Project getProject(String projectid){
//        Map<String,String> map =new HashMap<>();
//        map.put("statistics","true");
        String result = Util.get("/projects/"+projectid,"statistics=true");

        Project project = JSON.parseObject(result,Project.class);
        JSONObject jsonObject = JSON.parseObject(result);
//        System.out.println("??????"+jsonObject.getString("description"));
//        System.out.println("??????"+jsonObject.getInteger("commit_count"));
        JSONObject jsonObject1 = jsonObject.getJSONObject("statistics");
        project.setCommit_count(jsonObject1.getInteger("commit_count")+"");
        project.setRepository_size(jsonObject1.getString("repository_size"));

        return  project;
    }
}

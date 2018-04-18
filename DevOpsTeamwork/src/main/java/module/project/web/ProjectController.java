package module.project.web;

import com.alibaba.fastjson.JSONObject;
import module.group.service.GroupService;
import module.project.service.PManagerService;
import module.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liying on 2018/4/17.
 */
@Controller
@RequestMapping("/project")
public class ProjectController {


    @Autowired
    ProjectService projectservice;
    @Autowired
    PManagerService pManagerService;
    @Autowired
    GroupService groupService;

    @RequestMapping(value = "/createWithGroup", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createWithGroup(String projectName,String info,List<String> managerList,int groupId,String creatorName) {
        JSONObject toReturn = new JSONObject();
        try{
            projectservice.createWithGroup(projectName, info, managerList, groupId, creatorName);
            toReturn.put("success", true);
            toReturn.put("msg", "success");
        }catch (Exception e){
            toReturn.put("success", false);
            toReturn.put("msg", e.getMessage());

        }
        return toReturn.toString();
    }


    @RequestMapping(value = "/createWithoutGroup", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createWithoutGroup(String projectName,String info,List<String> managerList,List<String> memberList,String creatorName) {
        JSONObject toReturn = new JSONObject();
        //todo: 调用创建团队的service
        int groupId=1;
        try{
            projectservice.createWithGroup(projectName, info, managerList, groupId, creatorName);
            toReturn.put("success", true);
            toReturn.put("msg", "success");
        }catch (Exception e){
            toReturn.put("success", false);
            toReturn.put("msg", e.getMessage());

        }
        return toReturn.toString();
    }

    @RequestMapping(value = "/{projectId}/modify", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String modify(@PathVariable int projectId,String projectName, String info, String applicant) {
        JSONObject toReturn = new JSONObject();
        try {
            projectservice.editProject(projectId, projectName, info, applicant);
            toReturn.put("success", true);
            toReturn.put("msg", "success");
        }catch(Exception e){
            toReturn.put("success", false);
            toReturn.put("msg", e.getMessage());

        }
        return toReturn.toString();
    }




}

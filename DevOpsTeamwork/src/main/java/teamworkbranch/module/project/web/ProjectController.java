package teamworkbranch.module.project.web;

import com.alibaba.fastjson.JSONObject;
import teamworkbranch.exception.ExistedException;
import teamworkbranch.exception.NonprivilegedUserException;
import teamworkbranch.module.group.service.GroupService;
import teamworkbranch.module.project.model.Project;
import teamworkbranch.module.project.service.PManagerService;
import teamworkbranch.module.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liying on 2018/4/17.
 */
@RestController
@RequestMapping("/project")
public class ProjectController {


    @Autowired
    ProjectService projectService;
    @Autowired
    PManagerService pManagerService;

    @RequestMapping(value = "/createWithGroup", method = RequestMethod.POST)
    @ResponseBody
    public String createWithGroup(String projectName,String info,List<String> managerList,int groupId,String creatorName) {
        JSONObject toReturn = new JSONObject();
        try{
            projectService.createWithGroup(projectName, info, managerList, groupId, creatorName);
            toReturn.put("success", true);
            toReturn.put("msg", "success");
        }catch (Exception e){
            toReturn.put("success", false);
            toReturn.put("msg", e.getMessage());

        }
        return toReturn.toString();
    }


    @RequestMapping(value = "/createWithoutGroup", method = RequestMethod.POST)
    @ResponseBody
    public String createWithoutGroup(String projectName,String info,List<String> managerList,List<String> memberList,String creatorName) {
        JSONObject toReturn = new JSONObject();
        try{
            //todo: 调用创建团队的service
            int groupId=1;
            projectService.createWithGroup(projectName, info, managerList, groupId, creatorName);
            toReturn.put("success", true);
            toReturn.put("msg", "success");
        }catch (Exception e){
            toReturn.put("success", false);
            toReturn.put("msg", e.getMessage());

        }
        return toReturn.toString();
    }

    @RequestMapping(value = "/{projectId}/modify", method = RequestMethod.POST)
    @ResponseBody
    public String modify(@PathVariable int projectId,String projectName, String info, String applicant) {
        JSONObject toReturn = new JSONObject();
        try {
            projectService.editProject(projectId, projectName, info, applicant);
            toReturn.put("success", true);
            toReturn.put("msg", "success");
        }catch(Exception e){
            toReturn.put("success", false);
            toReturn.put("msg", e.getMessage());

        }
        return toReturn.toString();
    }


    @RequestMapping(value = "/{projectId}/getInfo", method = RequestMethod.GET)
    @ResponseBody
    public Project getInfo(@PathVariable int projectId) {
        Project project=projectService.getProjectInfo(projectId);

        return project;
    }

    @RequestMapping(value = "/getProjectList", method = RequestMethod.GET)
    @ResponseBody
    public List<Project> getProjectList(String username) {
        List<Project> project=projectService.getProjectsByUser(username);

        return project;
    }

    @RequestMapping(value = "/{projectId}/addPManager", method = RequestMethod.POST)
    @ResponseBody
    public String addManager(@PathVariable int projectId,String username,String applicant) {
        JSONObject toReturn = new JSONObject();
        try {
            pManagerService.addManager(projectId,username,applicant);
        } catch (NonprivilegedUserException e) {
            toReturn.put("success", true);
            toReturn.put("msg", "success");
        } catch (ExistedException e) {
            toReturn.put("success", true);
            toReturn.put("msg", "success");
        }


        return toReturn.toString();
    }
    @RequestMapping(value = "/{projectId}/deletePManager", method = RequestMethod.POST)
    @ResponseBody
    public String deleteManager(@PathVariable int projectId,String username,String applicant) {
        JSONObject toReturn = new JSONObject();
        try {
            pManagerService.deleteManager(projectId,username,applicant);
        } catch (NonprivilegedUserException e) {
            toReturn.put("success", true);
            toReturn.put("msg", "success");
        }
        return toReturn.toString();
    }




}

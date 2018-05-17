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
import teamworkbranch.util.GitlabInvoker;

import java.util.ArrayList;
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

    @Autowired
    GroupService groupService;
    @Autowired
    private GitlabInvoker gitlabInvoker;

    @RequestMapping(value = "/createWithGroup", method = RequestMethod.POST)
    @ResponseBody
    public String createWithGroup(String projectName, String info, ArrayList<String> managerList, Integer groupId, String creatorName, String tool) {
        JSONObject toReturn = new JSONObject();
        try{
            int projectId=projectService.createWithGroup(projectName, info, managerList, groupId, creatorName,tool);
            initial(projectId,groupId,projectName,info);
            toReturn.put("success", true);
            toReturn.put("msg", "项目ID为"+projectId);
        }catch (Exception e){
            toReturn.put("success", false);
            toReturn.put("msg", e.getMessage());
            e.printStackTrace();

        }
        return toReturn.toString();
    }


    @RequestMapping(value = "/createWithoutGroup", method = RequestMethod.POST)
    @ResponseBody
    public String createWithoutGroup(String projectName,String info,List<String> managerList,List<String> memberList,String creatorName,String tool) {
        JSONObject toReturn = new JSONObject();
        try{
            int groupId=groupService.createGroup(creatorName+"创建的group",creatorName+"创建的group",creatorName,memberList);
            int projectId=projectService.createWithGroup(projectName, info, managerList, groupId, creatorName,tool);
            initial(projectId,groupId,projectName,info);
            toReturn.put("success", true);
            toReturn.put("msg", "项目ID为"+projectId);
        }catch (Exception e){
            toReturn.put("success", false);
            toReturn.put("msg", e.getMessage());

        }
        return toReturn.toString();
    }

    @RequestMapping(value = "/{projectId}/modify", method = RequestMethod.POST)
    @ResponseBody
    public String modify(@PathVariable Integer projectId,String projectName, String info, String applicant) {
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
    public Project getInfo(@PathVariable Integer projectId) {
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
    public String addManager(@PathVariable Integer projectId,String username,String applicant) {
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
    public String deleteManager(@PathVariable Integer projectId,String username,String applicant) {
        JSONObject toReturn = new JSONObject();
        try {
            pManagerService.deleteManager(projectId,username,applicant);
        } catch (NonprivilegedUserException e) {
            toReturn.put("success", true);
            toReturn.put("msg", "success");
        }
        return toReturn.toString();
    }

    private void initial(int projectId,int groupId,String projectName,String description) throws Exception {
        String gitResult=gitlabInvoker.initialProject(projectId,groupId,projectName,description);
        System.out.println(gitResult);


    }



}

package teamworkbranch.module.project.web;

import com.alibaba.fastjson.JSONObject;
import teamworkbranch.exception.ExistedException;
import teamworkbranch.exception.JenkinsException;
import teamworkbranch.exception.NonprivilegedUserException;
import teamworkbranch.module.group.service.GroupService;
import teamworkbranch.module.project.model.Project;
import teamworkbranch.module.project.service.PManagerService;
import teamworkbranch.module.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import teamworkbranch.util.GitlabCIInvoker;
import teamworkbranch.util.GitlabInvoker;
import teamworkbranch.util.JenkinsInvoker;

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
    @Autowired
    private JenkinsInvoker jenkinsInvoker;

    @Autowired
    private GitlabCIInvoker gitlabCIInvoker;

    @RequestMapping(value = "/createWithGroupUseJenkins", method = RequestMethod.POST)
    @ResponseBody
    public String createWithGroupUseJenkins(String projectName, String info, ArrayList<String> managerList, Integer groupId, String creatorName, String jenkinsFilePath) {
        JSONObject toReturn = new JSONObject();
        try{
            int projectId=projectService.createWithGroup(projectName, info, managerList, groupId, creatorName,"jenkins");
            initialJenkins(projectId,groupId,projectName,info,jenkinsFilePath,creatorName);
            toReturn.put("success", true);
            toReturn.put("msg", "项目ID为"+projectId);
        }catch (Exception e){
            toReturn.put("success", false);
            toReturn.put("msg", e.getMessage());

        }
        return toReturn.toString();
    }
    @RequestMapping(value = "/createWithGroupUseGitlabCI", method = RequestMethod.POST)
    @ResponseBody
    public String createWithGroupUseGitLabCI(String projectName, String info, ArrayList<String> managerList, Integer groupId, String creatorName, String language) {
        JSONObject toReturn = new JSONObject();
        try{
            int projectId=projectService.createWithGroup(projectName, info, managerList, groupId, creatorName,"jenkins");
            initialGitlabCI(projectId,groupId,projectName,info,language,creatorName);
            toReturn.put("success", true);
            toReturn.put("msg", "项目ID为"+projectId);
        }catch (Exception e){
            toReturn.put("success", false);
            toReturn.put("msg", e.getMessage());
            e.printStackTrace();

        }
        return toReturn.toString();
    }


    @RequestMapping(value = "/createWithoutGroupUseJenkins", method = RequestMethod.POST)
    @ResponseBody
    public String createWithoutGroupUseJenkins(String projectName,String info,List<String> managerList,List<String> memberList,String creatorName,String jenkinsFilePath) {
        JSONObject toReturn = new JSONObject();
        try{
            int groupId=groupService.createGroup(creatorName+"创建的group",creatorName+"创建的group",creatorName,memberList);
            int projectId=projectService.createWithGroup(projectName, info, managerList, groupId, creatorName,"jenkins");
            initialJenkins(projectId,groupId,projectName,info,jenkinsFilePath,creatorName);
            toReturn.put("success", true);
            toReturn.put("msg", "项目ID为"+projectId);
        }catch (Exception e){
            toReturn.put("success", false);
            toReturn.put("msg", e.getMessage());

        }
        return toReturn.toString();
    }
    @RequestMapping(value = "/createWithoutGroupUseGitlabCI", method = RequestMethod.POST)
    @ResponseBody
    public String createWithoutGroupUseGitlabCI(String projectName,String info,List<String> managerList,List<String> memberList,String creatorName,String language) {
        JSONObject toReturn = new JSONObject();
        try{
            int groupId=groupService.createGroup(creatorName+"创建的group",creatorName+"创建的group",creatorName,memberList);
            int projectId=projectService.createWithGroup(projectName, info, managerList, groupId, creatorName,"jenkins");
            initialGitlabCI(projectId,groupId,projectName,info,language,creatorName);
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

    private void initialJenkins(int projectId,int groupId,String projectName,String description,String jenkinsFilePath,String creatorName) throws Exception {
        String gitResult=gitlabInvoker.initialProject(projectId,groupId,projectName,description,creatorName);
        System.out.println(gitResult);
        JSONObject jsonObject = JSONObject.parseObject(gitResult);
        String url= jsonObject.getString("ssh_url_to_repo");
        String groupName=groupService.getGroupInfo(groupId).getName();
        String jenkinsResult= jenkinsInvoker.initialProject(groupName,projectId,projectName,description,url,jenkinsFilePath);
        System.out.println(jenkinsResult);
        JSONObject jsonObject2 = JSONObject.parseObject(jenkinsResult);
        String success=jsonObject2.getString("success");
        String info=jsonObject2.getString("information");
        if(success.equals("false")){
            JenkinsException jenkinsException=new JenkinsException();
            jenkinsException.setMessage(info);
            throw jenkinsException;
        }

    }

    private void initialGitlabCI(int projectId,int groupId,String projectName,String description,String language,String creatorName) throws Exception {
        String gitResult=gitlabInvoker.initialProject(projectId,groupId,projectName,description,creatorName);
        String groupName=groupService.getGroupInfo(groupId).getName();
        String gitlabciResult= gitlabCIInvoker.initialProject(groupName,projectId,projectName,language);
        System.out.println(gitResult);
        System.out.println(gitlabciResult);
    }


}

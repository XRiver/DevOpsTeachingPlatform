package edu.nju.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import edu.nju.config.LogBean;
import edu.nju.model.Group;
import edu.nju.model.GroupMember;
import edu.nju.model.Project;
import edu.nju.service.GroupService;
import edu.nju.service.ProjectService;
import edu.nju.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @Autowired
    ProjectService projectService;

    @RequestMapping(value = "/group/newgroup" ,method = RequestMethod.POST)
    public String newGroup(@RequestParam Map<String,String> map) {
        String id = map.get("id");
        String name = map.get("name");
        String description = map.get("description");
        String visibility = map.get("visibility");

        String result=groupService.addGroup(name,name,description,visibility);

        //TODO
        //此处成功，return 空  ，待修改
        LogBean.log("增加group result："+result);
        if(result==null){
            LogBean.log("增加group fail, result =  null");
        }else {
            String gitlabID= JSON.parseObject(result,Group.class).getId();
            transferService.insertGroupID(id,gitlabID);
            String gitlabID2=transferService.getGitlabGroupIDByGroupID(id);
            LogBean.log("插入group 的gitlabID ："+gitlabID2);
        }

        return result;
    }

        @RequestMapping(value = "/group/newmember" ,method = RequestMethod.POST)
        public String addGroupMember(@RequestParam Map<String,String> map) {
            String userid = map.get("userid");
            String groupid = map.get("groupid");

            String gitlabuserID=transferService.getGitlabUserIDByUserID(userid);
            String gitlabgroupID=transferService.getGitlabGroupIDByGroupID(groupid);

            String result=groupService.addMemberToGroup(gitlabgroupID,gitlabuserID);

            LogBean.log("在group "+gitlabgroupID+"里插入成员："+gitlabuserID);
            return result;
        }

        @RequestMapping(value = "/group/removemember/{groupid}/{userid}",method = RequestMethod.DELETE)
        public String deleteGroupMember(){
            //TODO
            return "";
        }

        @RequestMapping(value = "/group/allmember/{groupid}",method = RequestMethod.GET)
        public String allGroupMember(@PathVariable String groupid){
            String gitlabID=transferService.getGitlabGroupIDByGroupID(groupid);
            List<GroupMember> groupMemberList = groupService.getMembersOfgroup(gitlabID);
            for(int i=0;i<groupMemberList.size();i++){
                groupMemberList.get(i).setId(transferService.getUserIDByGitlabID(groupMemberList.get(i).getId()));
            }
            String result=JSON.toJSONString(groupMemberList);
            LogBean.log("allGroupMember: "+result);
            return result;
        }

        @RequestMapping(value = "/project/newproject" , method = RequestMethod.POST)
        public String  newProject(@RequestParam Map<String,String> map){
            String groupid=map.get("groupid");
            String projectid=map.get("projectid");
            String name=map.get("name");
            String description=map.get("description");
            String userid=map.get("userid");

            String gitlabuserID=transferService.getGitlabUserIDByUserID(userid);
            String gitlabgroupID=transferService.getGitlabGroupIDByGroupID(groupid);

            String result=projectService.createProject(name,description,gitlabuserID);
            JSONObject jsonObject=JSON.parseObject(result);
            String projectgitlabid=jsonObject.getString("id");
            projectService.transferPro2Group(gitlabgroupID,projectgitlabid);
            transferService.insertProjectID(projectid,projectgitlabid);
            LogBean.log("new project ,name: "+name+";gitlabgroup: "+gitlabgroupID+";gitlabpro: "+projectgitlabid);
            return result;
        }

        @RequestMapping(value = "/project/{projectid}",method = RequestMethod.GET)
        public String getProject(@PathVariable String projectid){


            String projectgitlabid = transferService.getGitlabProjectIDByProjectID(projectid);
            Project project = projectService.getProject(projectgitlabid);
            String result = JSON.toJSONString(project);
            LogBean.log("get project :"+result);
            return result;
//            Project project=new Project();
//            project.setId("-1");
//            project.setCommit_count(100+"");
//            project.setDescription("todotodotodo");
//            project.setHttp_url_to_repo("https://github.com/yhqqq/TestProject3.git");
//            project.setName("TestProject3");
//            project.setRepository_size("10000kb");
//            project.setSsh_url_to_repo("git@github.com:yhqqq/TestProject3.git");
//            project.setWeb_url("https://github.com/yhqqq/TestProject3.git");
//            LogBean.log("get projetc info: "+projectid);
//            return JSON.toJSONString(project);

        }

}

package edu.nju.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import edu.nju.config.LogBean;
import edu.nju.model.Group;
import edu.nju.model.GroupMember;
import edu.nju.service.GroupService;
import edu.nju.service.ProjectService;
import edu.nju.service.TransferService;
import org.apache.ibatis.annotations.Param;
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
    public String newGroup(@RequestBody Map<String,String> map) {
        String id = map.get("id");
        String name = map.get("name");
        String description = map.get("description");
        String visibility = map.get("visibility");

        String result=groupService.addGroup(name,name,description,visibility);

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
        public String addGroupMember(@RequestBody Map<String,String> map) {
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
        public String  newProject(@RequestBody Map<String,String> map){
            String groupid=map.get("grouid");
            String projectid=map.get("projectid");
            String name=map.get("name");
            String description=map.get("description");

            String gitlabgroupID=transferService.getGitlabGroupIDByGroupID(groupid);

            String result=projectService.createProject(name);
            JSONObject jsonObject=JSON.parseObject(result);
            String projectgitlabid=jsonObject.getString("id");

            projectService.transferPro2Group(gitlabgroupID,projectgitlabid);
            LogBean.log("new project ,name: "+name+";gitlabgroup: "+gitlabgroupID+";gitlabpro: "+projectgitlabid);
            return result;
        }


}

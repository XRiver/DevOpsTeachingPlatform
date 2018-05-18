package teamworkbranch.module.group.web;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import teamworkbranch.exception.NotExistedException;
import teamworkbranch.module.group.model.GMember;
import teamworkbranch.module.group.model.Group;
import teamworkbranch.module.group.service.GMemberService;
import teamworkbranch.module.group.service.GroupService;
import teamworkbranch.util.GitlabInvoker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liying on 2018/4/17.
 */
@Controller
@RequestMapping("/group")
public class GroupController {


    @Autowired
    GroupService groupService;
    @Autowired
    GMemberService gMemberService;
    @Autowired
    private GitlabInvoker gitlabInvoker;

    @RequestMapping(value = "/createGroup", method = RequestMethod.POST)
    @ResponseBody
    public String createGroup(String groupName, String info, String creatorName, ArrayList<String> memberList, String visibility) {
        JSONObject toReturn = new JSONObject();
        try{
            int groupId = groupService.createGroup(groupName, info,creatorName,memberList);
            initialGroup(groupId,groupName,info,visibility);
            for(String member: memberList){
                initialGMember(groupId,member);
            }
            toReturn.put("success", true);
            toReturn.put("msg", "success");
            toReturn.put("msg", "团队ID为"+groupId);
        }catch (Exception e){
            toReturn.put("success", false);
            toReturn.put("msg", e.getMessage());
            e.printStackTrace();

        }
        return toReturn.toString();
    }

    @RequestMapping(value = "/{groupId}/deleteGroup", method = RequestMethod.POST)
    @ResponseBody
    public String deleteGroup(@PathVariable Integer groupId, String memberName) {
        JSONObject toReturn = new JSONObject();
        try{
            groupService.deleteGroup(groupId,memberName);
            toReturn.put("success", true);
            toReturn.put("msg", "success");
        }catch (Exception e){
            toReturn.put("success", false);
            toReturn.put("msg", e.getMessage());

        }
        return toReturn.toString();
    }

    @RequestMapping(value = "/{groupId}/editGroup", method = RequestMethod.POST)
    @ResponseBody
    public String editGroup(String name,String info,@PathVariable Integer groupId,String memberName) {
        JSONObject toReturn = new JSONObject();
        try {
            groupService.editGroup(name, info, groupId,memberName);
            toReturn.put("success", true);
            toReturn.put("msg", "success");
        } catch (Exception e) {
            toReturn.put("success", false);
            toReturn.put("msg", e.getMessage());

        }
        return toReturn.toString();
    }

    @RequestMapping(value = "/{groupId}/getGroup", method = RequestMethod.GET)
    @ResponseBody
    public Group getGroup(@PathVariable Integer groupId){
        Group group=groupService.getGroupInfo(groupId);
        return group;
    }

    @RequestMapping(value = "/{groupId}/getGMembers", method = RequestMethod.GET)
    @ResponseBody
    public List<GMember> getGMembers(@PathVariable Integer groupId) throws NotExistedException {
        List<GMember> gMembers = groupService.getMemberList(groupId);
        return gMembers;
    }

    private void initialGroup(int groupId,String groupName,String description,String visibility) throws Exception {
        String gitResult=gitlabInvoker.initialGroup(String.valueOf(groupId),groupName,description,visibility);
        JSONObject jsonObject = JSONObject.parseObject(gitResult);
        String des = jsonObject.getString("description");
        String url= jsonObject.getString("ssh_url_to_repo");


    }

    private void initialGMember(int groupId,String userName) throws Exception {
        String gitResult=gitlabInvoker.initialGMember(String.valueOf(groupId),userName);
        JSONObject jsonObject = JSONObject.parseObject(gitResult);
        String des = jsonObject.getString("description");
        String url= jsonObject.getString("ssh_url_to_repo");


    }

}

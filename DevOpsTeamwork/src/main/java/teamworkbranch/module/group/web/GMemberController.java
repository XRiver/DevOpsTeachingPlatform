package teamworkbranch.module.group.web;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import teamworkbranch.module.group.model.Group;
import teamworkbranch.module.group.service.GMemberService;
import teamworkbranch.module.group.service.GroupService;
import teamworkbranch.util.GitlabInvoker;

import java.util.List;

/**
 * Created by caosh on 2018/4/24.
 */
@Controller
@RequestMapping("/group")
public class GMemberController {

    @Autowired
    GroupService groupService;
    @Autowired
    GMemberService gMemberService;

    @Autowired
    private GitlabInvoker gitlabInvoker;

    @RequestMapping(value = "/{groupId}/addGMember", method = RequestMethod.POST)
    @ResponseBody
    public String addGMember(@PathVariable int groupId, String userName, int is_manager,String memberName) {
        JSONObject toReturn = new JSONObject();
        try{
            gMemberService.addMember(groupId,userName,is_manager,memberName);
            initialGMember(groupId,userName);
            toReturn.put("success", true);
            toReturn.put("msg", "success");
        }catch (Exception e){
            toReturn.put("success", false);
            toReturn.put("msg", e.getMessage());

        }
        return toReturn.toString();
    }


    @RequestMapping(value = "/{groupId}/removeGMember", method = RequestMethod.POST)
    @ResponseBody
    public String removeGMember(@PathVariable int groupId,String userName,String memberName) {
        JSONObject toReturn = new JSONObject();
        try{
            gMemberService.removeMember(groupId,userName,memberName);
            toReturn.put("success", true);
            toReturn.put("msg", "success");
        }catch (Exception e){
            toReturn.put("success", false);
            toReturn.put("msg", e.getMessage());

        }
        return toReturn.toString();
    }

    @RequestMapping(value = "/{groupId}/leaveGroup", method = RequestMethod.POST)
    @ResponseBody
    public String leaveGroup(@PathVariable int groupId,String userName) {
        JSONObject toReturn = new JSONObject();
        try{
            gMemberService.leaveGroup(groupId,userName);
            toReturn.put("success", true);
            toReturn.put("msg", "success");
        }catch (Exception e){
            toReturn.put("success", false);
            toReturn.put("msg", e.getMessage());

        }
        return toReturn.toString();
    }


    @RequestMapping(value = "/{groupId}/editGMember", method = RequestMethod.POST)
    @ResponseBody
    public String editGMember(@PathVariable int groupId,String userName,int is_manager,String memberName) {
        JSONObject toReturn = new JSONObject();
        try{
            gMemberService.editMember(groupId,userName,is_manager,memberName);
            toReturn.put("success", true);
            toReturn.put("msg", "success");
        }catch (Exception e){
            toReturn.put("success", false);
            toReturn.put("msg", e.getMessage());

        }
        return toReturn.toString();
    }

    @RequestMapping(value = "/{memberName}/getMyGroups", method = RequestMethod.GET)
    @ResponseBody
    public List<Group> getMyGroups(@PathVariable String memberName){
        List<Group> groups = groupService.getGroupList(memberName);
        return groups;
    }

    private void initialGMember(int groupId,String userName) throws Exception {
        String gitResult=gitlabInvoker.initialGMember(String.valueOf(groupId),userName);
        JSONObject jsonObject = JSONObject.parseObject(gitResult);
        String des = jsonObject.getString("description");
        String url= jsonObject.getString("ssh_url_to_repo");


    }

}

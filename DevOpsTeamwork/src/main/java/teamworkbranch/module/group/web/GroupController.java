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

    @RequestMapping(value = "/createGroup", method = RequestMethod.POST)
    @ResponseBody
    public String createGroup(String groupName, String info, String creatorName, List<String> memberList) {
        JSONObject toReturn = new JSONObject();
        try{
            groupService.createGroup(groupName, info,creatorName,memberList);
            toReturn.put("success", true);
            toReturn.put("msg", "success");
        }catch (Exception e){
            toReturn.put("success", false);
            toReturn.put("msg", e.getMessage());

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
    public Group getGroup(@PathVariable Integer groupId) throws NotExistedException {
        Group group=groupService.getGroupInfo(groupId);
        return group;
    }

    @RequestMapping(value = "/{groupId}/getGMembers", method = RequestMethod.GET)
    @ResponseBody
    public List<GMember> getGMembers(@PathVariable Integer groupId) throws NotExistedException {
        List<GMember> gMembers = groupService.getMemberList(groupId);
        return gMembers;
    }

}

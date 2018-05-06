package teamworkbranch.module.group.web;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import teamworkbranch.exception.NotExistedException;
import teamworkbranch.module.group.model.Group;
import teamworkbranch.module.group.service.GMemberService;
import teamworkbranch.module.group.service.GroupService;

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

    @RequestMapping(value = "/{groupId}/addGMember", method = RequestMethod.POST)
    @ResponseBody
    public String addGMember(@PathVariable int groupId, String userName, int is_manager) {
        JSONObject toReturn = new JSONObject();
        try{
            gMemberService.addMember(groupId,userName,is_manager);
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
    public String removeGMember(@PathVariable int groupId,String userName) {
        JSONObject toReturn = new JSONObject();
        try{
            gMemberService.removeMember(groupId,userName);
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
    public String editGMember(@PathVariable int groupId,String userName,int is_manager) {
        JSONObject toReturn = new JSONObject();
        try{
            gMemberService.editMember(groupId,userName,is_manager);
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
    public List<Group> getMyGroups(@PathVariable String memberName) throws NotExistedException {
        List<Group> groups = groupService.getGroupList(memberName);
        return groups;
    }

}

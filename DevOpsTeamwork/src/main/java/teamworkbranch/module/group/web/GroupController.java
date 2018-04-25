package teamworkbranch.module.group.web;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import teamworkbranch.exception.NotExistedException;
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

    @RequestMapping(value = "/createGroup", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
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

    @RequestMapping(value = "/deleteGroup", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deleteGroup(int groupId,String memberName) {
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

    @RequestMapping(value = "/editGroup", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String editGroup(String name,String info,int groupId,String memberName) {
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

    @RequestMapping(value = "/getGroup", method = RequestMethod.GET)
    @ResponseBody
    public Group getGroup(int groupId) throws NotExistedException {
        Group group=groupService.getGroupInfo(groupId);
//            groupService.getMemberList(groupId);
        return group;
    }

}

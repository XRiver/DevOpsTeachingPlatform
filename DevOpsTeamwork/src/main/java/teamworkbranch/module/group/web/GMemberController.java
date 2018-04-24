package teamworkbranch.module.group.web;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import teamworkbranch.module.group.service.GMemberService;
import teamworkbranch.module.group.service.GroupService;

/**
 * Created by caosh on 2018/4/24.
 */
@Controller
@RequestMapping("/GMember")
public class GMemberController {

    @Autowired
    GroupService groupService;
    @Autowired
    GMemberService gMemberService;

    @RequestMapping(value = "/addGMember", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addGMember(int groupId,String userName,int is_manager) {
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


    @RequestMapping(value = "/removeGMember", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String removeGMember(int groupId,String userName) {
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

    @RequestMapping(value = "/editGMember", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String editGMember(int groupId,String userName,int is_manager) {
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

    @RequestMapping(value = "/getMyGroups", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getMyGroups(String memberName) {
        JSONObject toReturn = new JSONObject();
        try{
            groupService.getGroupList(memberName);
            toReturn.put("success", true);
            toReturn.put("msg", "success");
        }catch (Exception e){
            toReturn.put("success", false);
            toReturn.put("msg", e.getMessage());

        }
        return toReturn.toString();
    }

}

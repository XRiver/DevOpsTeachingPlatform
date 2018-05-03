package teamworkbranch.module.log.web;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import teamworkbranch.exception.NotExistedException;
import teamworkbranch.module.log.model.Log;
import teamworkbranch.module.log.service.LogService;

import java.util.List;

/**
 * Created by liying on 2018/4/17.
 */
@RestController
@RequestMapping("/project")
public class LogController {


    @Autowired
    LogService logService;


    @RequestMapping(value = "{projectId}/getLogs", method = RequestMethod.GET)
    @ResponseBody
    public List<Log> getLogs(@PathVariable int projectId) throws NotExistedException {
        List<Log> logs = logService.getLogByProject(projectId);
        return logs;
    }

    @RequestMapping(value = "{projectId}/deleteLog", method = RequestMethod.POST)
    @ResponseBody
    public String  deleteLog(@PathVariable int projectId,int logId,String username) throws NotExistedException {
        JSONObject toReturn = new JSONObject();
        try {
            logService.deleteLog(logId,username);
            toReturn.put("success", true);
            toReturn.put("msg", "success");
        }catch(Exception e){
            toReturn.put("success", false);
            toReturn.put("msg", e.getMessage());

        }
        return toReturn.toString();
    }




}

package com.Controller;

import com.DataVO.BugChangeVO;
import com.DataVO.BugVO;
import com.DataVO.MyResponseData;
import com.Service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2018/3/23.
 */

@RestController
public class BugController {
    @Autowired
    BugService bugService;


    @RequestMapping(value = "/bug/create", method = RequestMethod.POST)
    public MyResponseData<Boolean> createBug(@RequestBody BugVO bugVO){
        bugService.createBug(bugVO);
        return new MyResponseData<Boolean>("succeed", new String[]{"成功创建缺陷！"}, true);
    }

    @RequestMapping(value = "/bug/delete", method = RequestMethod.POST)
    public MyResponseData<Boolean> deleteBug(@RequestParam("id") long id){
        bugService.deleteBug(id);
        return new MyResponseData<Boolean>("succeed", new String[]{"成功删除缺陷！"}, true);
    }

    @RequestMapping(value = "/bug/update", method = RequestMethod.POST)
    public MyResponseData<Boolean> updateBug(@RequestBody BugVO bugVO){
        bugService.updateBug(bugVO);
        return new MyResponseData<Boolean>("succeed", new String[]{"成功更新缺陷！"}, true);
    }

    @RequestMapping(value = "/bug/get-by-project", method = RequestMethod.GET)
    public List<BugVO> getBugByProject(@RequestParam("projectId") String id){
        List<BugVO> bugs=bugService.getBugByProject(id);
        return bugs;
    }

    @RequestMapping(value = "/bug/get-by-id", method = RequestMethod.GET)
    public BugVO getBugById(@RequestParam("id") long id){
        BugVO bug=bugService.getBugById(id);
        return bug;
    }

    @RequestMapping(value = "/bug/change", method = RequestMethod.POST)
    public MyResponseData<Boolean> changeBug(@RequestParam("bugId") long bugId, @RequestParam("change")BugChangeVO bugChangeVO){
        bugService.createBugChange(bugChangeVO,bugId);
        return new MyResponseData<Boolean>("succeed", new String[]{"成功更改缺陷！"}, true);
    }

}

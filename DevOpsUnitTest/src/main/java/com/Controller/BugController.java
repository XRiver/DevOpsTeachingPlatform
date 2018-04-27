package com.Controller;

import com.Common.BugState;
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
    public MyResponseData<Boolean> createBug(@RequestParam("projectId") String projectId,
                                             @RequestParam("name") String name,@RequestParam("info") String info,
                                             @RequestParam("imp") String imp){

        BugVO bugVO=new BugVO();
        bugVO.setName(name);
        bugVO.setProject_id(projectId);
        bugVO.setImportance(imp);
        bugVO.setInfo(info);
        bugVO.setState("新建");
        bugService.createBug(bugVO);
        return new MyResponseData<Boolean>("succeed", new String[]{"成功创建缺陷！"}, true);
    }

    @RequestMapping(value = "/bug/delete", method = RequestMethod.POST)
    public MyResponseData<Boolean> deleteBug(@RequestParam("id") long id){
        bugService.deleteBug(id);
        return new MyResponseData<Boolean>("succeed", new String[]{"成功删除缺陷！"}, true);
    }

    @RequestMapping(value = "/bug/update", method = RequestMethod.POST)
    public MyResponseData<Boolean> updateBug(@RequestParam("id") long id,
                                             @RequestParam("name") String name,@RequestParam("info") String info,
                                             @RequestParam("imp") String imp){
        BugVO bugVO=new BugVO();
        bugVO.setId(id);
        bugVO.setName(name);
        bugVO.setImportance(imp);
        bugVO.setInfo(info);
        bugService.updateBug(bugVO);
        return new MyResponseData<Boolean>("succeed", new String[]{"成功更新缺陷！"}, true);
    }

    @RequestMapping(value = "/bug/get-by-project", method = RequestMethod.GET)
    public List<BugVO> getBugByProject(@RequestParam("projectId") String id){
        List<BugVO> bugs=bugService.getBugByProject(id);
        return bugs;
    }

    @RequestMapping(value = "/bug/get", method = RequestMethod.GET)
    public BugVO getBugById(@RequestParam("id") long id){
        BugVO bug=bugService.getBugById(id);
        return bug;
    }

    @RequestMapping(value = "/bug/change", method = RequestMethod.POST)
    public MyResponseData<Boolean> changeBug(@RequestParam("id") long bugId, @RequestParam("state")String state,
                                             @RequestParam("info")String info, @RequestParam("manager")String manager){
        BugChangeVO bugChangeVO=new BugChangeVO();
        bugChangeVO.setInfo(info);
        bugChangeVO.setAfter_state(state);
        bugChangeVO.setManager(manager);
        bugService.createBugChange(bugChangeVO,bugId);
        return new MyResponseData<Boolean>("succeed", new String[]{"成功更改缺陷！"}, true);
    }

}

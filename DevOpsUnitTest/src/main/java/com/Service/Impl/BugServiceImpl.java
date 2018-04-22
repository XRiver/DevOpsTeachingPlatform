package com.Service.Impl;

import com.Common.BugState;
import com.DataVO.BugChangeVO;
import com.DataVO.BugVO;
import com.Entity.Bug;
import com.Entity.BugChange;
import com.Repository.BugRepository;
import com.Service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/3/20.
 */
@Service
public class BugServiceImpl implements BugService{
    @Autowired
    private BugRepository bugRepository;

    @Override
    public boolean createBug(BugVO bugVO) {
        Bug bug=new Bug(bugVO);
        bug.setState(BugState.newbuilt.toString());
        bugRepository.save(bug);
        return true;
    }

    @Override
    public boolean deleteBug(Long id) {
        bugRepository.delete(id);
        bugRepository.flush();
        return true;
    }

    @Override
    public boolean updateBug(BugVO bugVO) {
        if(bugVO.getId()==0){
            System.out.println("id 不存在");
            return false;
        }
        Bug bug=new Bug(bugVO);
        bug.setId(bugVO.getId());
        bugRepository.saveAndFlush(bug);
        return true;
    }

    @Override
    public List<BugVO> getBugByProject(String projectId) {

        List<BugVO> list =new ArrayList<BugVO>();
        for(Bug bug:bugRepository.findByProject_id(projectId)){
            list.add(bug.toBugVO());
        }

        return list;
    }

    @Override
    public BugVO getBugById(Long id) {
        if(bugRepository.findById(id)==null){
            System.out.println("id 不存在");
            return null;
        }
        return bugRepository.findById(id).toBugVO();
    }

    @Override
    public boolean createBugChange(BugChangeVO bugChangeVO, Long bugId) {
        if(bugChangeVO.getAfter_state()==null||bugChangeVO.getAfter_state().equals("")){
            System.out.println("没有设置状态");
            return false;
        }

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        bugChangeVO.setTime(sdf.format(d));
        BugChange bugChange=new BugChange(bugChangeVO);
        Bug bug=bugRepository.findById(bugId);
        bugChange.setBefore_state(bug.getState());
        bug.setState(bugChange.getAfter_state());
        bug.addBug_change(bugChange);
        bugRepository.saveAndFlush(bug);

        return true;
    }
}

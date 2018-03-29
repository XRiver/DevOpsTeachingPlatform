package com.Service.Impl;

import com.DataVO.BugChangeVO;
import com.DataVO.BugVO;
import com.Entity.Bug;
import com.Entity.BugChange;
import com.Repository.BugRepository;
import com.Service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        bugRepository.save(new Bug(bugVO));
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
        return bugRepository.findById(id).toBugVO();
    }

    @Override
    public boolean createBugChange(BugChangeVO bugChangeVO, Long bugId) {
        BugChange bugChange=new BugChange(bugChangeVO);
        Bug bug=bugRepository.findById(bugId);
        bug.addBug_change(bugChange);
        bugRepository.saveAndFlush(bug);

        return true;
    }
}

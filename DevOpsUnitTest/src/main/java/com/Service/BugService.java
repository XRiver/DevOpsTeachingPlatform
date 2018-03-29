package com.Service;

import com.DataVO.BugChangeVO;
import com.DataVO.BugVO;

import java.util.List;

/**
 * Created by Administrator on 2018/3/20.
 */
public interface BugService {
    public boolean createBug(BugVO bugVO);

    public boolean deleteBug(Long id);

    public boolean updateBug(BugVO bugVO);

    public List<BugVO> getBugByProject(String projectId);

    public BugVO getBugById(Long id);

    public boolean createBugChange(BugChangeVO bugChangeVO,Long bugId);
}

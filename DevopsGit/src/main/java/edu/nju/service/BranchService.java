package edu.nju.service;

import com.alibaba.fastjson.JSON;
import edu.nju.api.Util;
import edu.nju.model.Branch;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yhq on 2018/4/1.
 */
@Service
public class BranchService {

    /**
     * GET /projects/:id/repository/branches
     * @param projectID
     * @return
     */
    public List<Branch> getBranchList(String projectID){
        String result= Util.get("/projects/"+projectID+"/repository/branches",null);
        List<Branch> branchList= JSON.parseArray(result,Branch.class);
        return branchList;
    }
}

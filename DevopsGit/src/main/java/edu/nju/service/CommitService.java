package edu.nju.service;

import edu.nju.api.Util;
import edu.nju.config.LogBean;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/4/1.
 */
@Service
public class CommitService {

    /**
     * TODO
     * @param projectID
     * @return
     */
    public String getCommits(String projectID){
        String result= Util.get("/projects/"+projectID+"/repository/commits",null);
        LogBean.log("getCommits result:"+result);

        return result;
    }
}

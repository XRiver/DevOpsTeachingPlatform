package edu.nju.service;

import edu.nju.api.Util;
import edu.nju.config.LogBean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yhq on 2018/4/1.
 */
@Service
public class MergeService {

    /**
     * TODO
     * @param projectID
     * @return
     */
    public String getMerges(String projectID){
        String result= Util.get("/projects/"+projectID+"/merge_requests",null);
        LogBean.log("get Merges: "+result);

        return result;
    }

    /**
     * TODO
     * PUT /projects/:id/merge_requests/:merge_request_iid/merge
     * @param projectID
     * @param mergeID
     * @return
     */
    public String acceptMerge(String projectID,String mergeID){
        Map<String,String> paramMap=new HashMap<String,String>();
        paramMap.put("id",projectID);
        paramMap.put("merge_request_iid",mergeID);
        String result = Util.put("/projects/"+projectID+"/merge_requests/"+mergeID+"/merge",paramMap);
        LogBean.log("acceptMerge : "+result);
        return result;
    }
}

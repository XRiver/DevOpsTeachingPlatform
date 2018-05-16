package edu.nju.service;

import com.alibaba.fastjson.JSON;
import edu.nju.api.Util;
import edu.nju.config.LogBean;
import edu.nju.model.FileNode;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yhq on 2018/3/25.
 */
@Service
public class RepositoryService {

    /**
     * GET  /file/archive/{projected}
     * GET /projects/:id/repository/archive
     * @param projectID
     * @return
     */
    public String getArchive(String projectID){
        String result= Util.get("/projects/"+projectID+"/repository/archive",
                "id="+projectID);
        return result;
    }

    /**
     * GET /projects/:id/repository/tree
     * @param projectID
     * @return
     */
    public List<FileNode> getTree2(String projectID,String file_path){
        String param= "path="+file_path;
        String result=Util.get("/projects/"+projectID+"/repository/tree",
                param);
        List<FileNode> nodeList = JSON.parseArray(result,FileNode.class);
        return nodeList;
    }

    /**
     * GET /projects/:id/repository/tree
     * @param projectID
     * @return
     */
    public List<FileNode> getTree(String projectID){
        String result=Util.get("/projects/"+projectID+"/repository/tree",
                null);
        List<FileNode> nodeList = JSON.parseArray(result,FileNode.class);
        return nodeList;
    }

    /**
     * GET /projects/:id/repository/files/:file_path/raw
     * @param projectID
     * @param filepath
     * @param ref
     * @return
     */
    public String getFile(String projectID,String filepath,String ref){
        String result=Util.get("/projects/"+projectID+"/repository/files/"+filepath+"/raw",
                "ref="+ref);
        return result;
    }

    /**
     * PUT /projects/:id/repository/files/:file_path
     * @param projectID
     * @param filepath
     * @param branch
     * @param content
     * @param commit_message
     * @return
     */
    public String uploadFile(String projectID,String filepath,String branch,
                             String content,String commit_message){
        Map<String,String> paramMap=new HashMap<String,String>();
        paramMap.put("file_path",filepath);
        paramMap.put("branch",branch);
        paramMap.put("content",content);
        paramMap.put("commit_message",commit_message);

        String result=Util.put("/projects/"+projectID+"/repository/files/"+filepath,
                paramMap);

        return result;
    }

    /**
     * TODO
     * @return
     */
    public String deleteFile(String projectid,String file_path,
                             String branch,String commit_message){
        LogBean.log("delete file service not write");
        return "";
    }
}

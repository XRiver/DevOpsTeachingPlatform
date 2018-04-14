package edu.nju.controller;

import com.alibaba.fastjson.JSON;
import edu.nju.config.LogBean;
import edu.nju.model.Branch;
import edu.nju.model.Commit;
import edu.nju.model.FileNode;
import edu.nju.model.MergeRequest;
import edu.nju.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/14.
 */
@RestController
public class RepositoryController {

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    TransferService transferService;

    @Autowired
    BranchService branchService;

    @Autowired
    CommitService commitService;

    @Autowired
    MergeService mergeService;

    @RequestMapping(value = "file/archive/{projectid}", method = RequestMethod.GET)
    public String allfile(@PathVariable String projectid) {
        String gitlabID = transferService.getGitlabProjectIDByProjectID(projectid);
        String result = repositoryService.getArchive(gitlabID);
        LogBean.log("get archive file.");
        return result;
    }

    @RequestMapping(value = "file/rawfile/{projectid}/{file_path}/{ref}", method = RequestMethod.GET)
    public String rawfile(@PathVariable("projectid") String projectid,
                          @PathVariable("file_path") String file_path,
                          @PathVariable("ref") String ref) {
        String gitlabID = transferService.getGitlabProjectIDByProjectID(projectid);
        String result = repositoryService.getFile(gitlabID, file_path, ref);
        LogBean.log("get raw file.");
        return result;
    }

    @RequestMapping(value = "file/tree/{projectid}", method = RequestMethod.GET)
    public String getfileTrees(@PathVariable String projectid){
        String gitlabID = transferService.getGitlabProjectIDByProjectID(projectid);
        List<FileNode> fileNodeList = repositoryService.getTree(gitlabID);
        String result = JSON.toJSONString(fileNodeList);
        LogBean.log("file tree: "+result);
        return result;
    }

    @RequestMapping(value = "/repository/allbranch/{projectid}", method = RequestMethod.GET)
    public String allbranches(@PathVariable String projectid) {
        String gitlabID = transferService.getGitlabProjectIDByProjectID(projectid);
        List<Branch> branchList = branchService.getBranchList(gitlabID);
        String result = JSON.toJSONString(branchList);
        LogBean.log("allbeanches: " + result);
        return result;
    }

    @RequestMapping(value = "/repository/allcommit/{projectid}", method = RequestMethod.GET)
    public String allcommits(@PathVariable String projectid) {
        String gitlabID = transferService.getGitlabProjectIDByProjectID(projectid);
        List<Commit> commitList = JSON.parseArray(commitService.getCommits(gitlabID), Commit.class);
        String result = JSON.toJSONString(commitList);
        LogBean.log("all commits: " + result);
        return result;
    }

    @RequestMapping(value = "/repository/allmergerequest/{projectid}", method = RequestMethod.GET)
    public String allMergeRequest(@PathVariable String projectid) {
        String gitlabID = transferService.getGitlabProjectIDByProjectID(projectid);
        List<MergeRequest> mergeRequestList = JSON.parseArray(mergeService.getMerges(gitlabID), MergeRequest.class);
        String result = JSON.toJSONString(mergeRequestList);
        LogBean.log("all merge requests : " + result);
        return result;
    }

    @RequestMapping(value = "/repository/mergerequest/{projectid}/{merge_request_iid}", method = RequestMethod.PUT)
    public String acceptMergeRequest(@PathVariable("projectid") String projectid,
                                     @PathVariable("merge_request_iid") String iid) {
        String gitlabID = transferService.getGitlabProjectIDByProjectID(projectid);
        String result = mergeService.acceptMerge(gitlabID, iid);
        LogBean.log("accept requests : " + result);
        return result;
    }

    @RequestMapping(value = "/repository/mergerequest/{projectid}/{merge_request_iid}",method = RequestMethod.DELETE)
    public String refuseMergeRequest(@PathVariable("projectid") String projectid,
                                     @PathVariable("merge_request_iid") String iid){
        //TODO
        return "";
    }

    @RequestMapping(value = "file/newfile/{projectid}/{file_path}",method = RequestMethod.PUT)
    public String uploadFile(@PathVariable("projectid") String projectid,
                             @PathVariable("file_path") String file_path,
                             @RequestBody Map<String,String> map){
        String gitlabID = transferService.getGitlabProjectIDByProjectID(projectid);
        String content = map.get("content");
        String commit_message=map.get("commit_message");
        String branch = map.get("branch");

        String result=repositoryService.uploadFile(gitlabID,file_path,branch,content,commit_message);
        LogBean.log("upload file result: "+ result);

        return result;
    }

    @RequestMapping(value = "/file/deletefile/{projectid}/{file_path}",method = RequestMethod.DELETE)
    public String deleteFile(@PathVariable("projectid") String projectid,
                             @PathVariable("file_path") String file_path,
                             @RequestBody Map<String,String> map){
        String gitlabID = transferService.getGitlabProjectIDByProjectID(projectid);
        String commit_message=map.get("commit_message");
        String branch = map.get("branch");

        String result =repositoryService.deleteFile(gitlabID,file_path,branch,commit_message);
        LogBean.log("delete file result: "+ result);


        return "";
    }
}

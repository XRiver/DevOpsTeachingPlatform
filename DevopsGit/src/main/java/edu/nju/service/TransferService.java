package edu.nju.service;

import edu.nju.data.GroupMapper;
import edu.nju.data.ProjectMapper;
import edu.nju.data.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yhq on 2018/4/1.
 * 提供团队管理模块用户相关ID与gitlab分配的ID的转换
 */
@Service
public class TransferService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    GroupMapper groupMapper;

    @Autowired
    ProjectMapper projectMapper;

    //TODO
    //另一个方向的转换

    public String getGitlabUserIDByUserID(String userID){
        return userMapper.getGitlabID(userID);
    }

    public String getGitlabProjectIDByProjectID(String ProjectID){
        return projectMapper.getGitlabID(ProjectID);
    }

    public String getGitlabGroupIDByGroupID(String GroupID){
        return groupMapper.getGitlabID(GroupID);
    }

    public String insertUserID(String userID,String gitlabID){
        return userMapper.insert(userID,gitlabID);
    }
    public String insertProjectID(String ProjectID,String gitlabID){
        return projectMapper.insert(ProjectID,gitlabID);
    }
    public String insertGroupID(String GroupID,String gitlabID){
        return groupMapper.insert(GroupID,gitlabID);
    }
}

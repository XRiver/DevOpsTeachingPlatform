package teamworkbranch.module.group.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamworkbranch.module.entity.VO.GroupVO;
import teamworkbranch.module.entity.VO.UserVO;
import teamworkbranch.module.group.dao.GMemberMapper;
import teamworkbranch.module.group.dao.GroupMapper;
import teamworkbranch.module.group.model.GMember;
import teamworkbranch.module.group.model.Group;
import teamworkbranch.module.group.service.GroupService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caosh on 2018/4/9.
 */
@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupMapper groupMapper;
    @Autowired
    GMemberMapper gMemberMapper;


    /**
     * 创建团队
     * @param groupName
     * @param info
     * @param creatorName
     * @return
     */
    public int createGroup(String groupName, String info, String creatorName, List<String> memberList) {
        Group group = new Group(groupName,info,creatorName);
        groupMapper.insertGroup(group);
        int groupId =  groupMapper.selectLastId();
        for(String member: memberList){
            GMember gMember = new GMember(groupId,member);
        }

        return 0;
    }

    /**
     * 删除团队
     * @param groupId
     * @return
     */
    public boolean deleteGroup(int groupId) {
        groupMapper.deleteGroup(groupId);
        return true;
    }

    /**
     * 编辑团队信息
     * @param name
     * @param info
     * @param groupId
     * @return
     */
    public boolean editGroup(String name,String info,int groupId) {
        return true;
    }

    /**
     * 团队修改人员
     * @param userId
     * @return
     */
    public boolean editMember(int userId) {
        return false;
    }

    /**
     * 查看团队信息
     * @param groupId
     * @return
     */
    public GroupVO getGroupInfo(int groupId) {
        return null;
    }

    /**
     * 查看团队成员
     * @param groupId
     * @return
     */
    public ArrayList<UserVO> getMemberList(int groupId) {
        return null;
    }

    /**
     * 查看所属团队
     * @param userId
     * @return
     */
    public ArrayList<GroupVO> getGroupList(int userId) {
        return null;
    }


}

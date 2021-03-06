package teamworkbranch.module.group.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamworkbranch.exception.NonprivilegedUserException;
import teamworkbranch.exception.NotExistedException;
import teamworkbranch.module.group.dao.GMemberMapper;
import teamworkbranch.module.group.dao.GroupMapper;
import teamworkbranch.module.group.model.GMember;
import teamworkbranch.module.group.model.Group;
import teamworkbranch.module.group.service.GroupService;

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
            GMember gMember = new GMember(groupId,member,0);
            gMemberMapper.insertGMember(gMember);
        }

        return groupId;
    }

    /**
     * 删除团队
     * @param groupId
     * @param memberName
     * @return
     */
    public boolean deleteGroup(int groupId,String memberName) throws NotExistedException, NonprivilegedUserException {
        GMember gMember = gMemberMapper.selectById(groupId,memberName);
        if(gMember==null){
            throw new NotExistedException();
        }else if(gMember.getIs_manager()==0){
            throw new NonprivilegedUserException();
        }
        else {
            groupMapper.deleteGroup(groupId);
        }
        return true;
    }

    /**
     * 编辑团队信息
     * @param name
     * @param info
     * @param groupId
     * @return
     */
    public boolean editGroup(String name,String info,int groupId,String memberName) throws NotExistedException, NonprivilegedUserException {
        Group group = groupMapper.selectById(groupId);
//        System.out.println("aaaaaa");
        System.out.println(group.getName());
//        System.out.println(group.getInfo());
        GMember gMember = gMemberMapper.selectById(groupId,memberName);
        if(gMember==null || group==null){
            System.out.println("不存在");
            throw new NotExistedException();
        }else if(gMember.getIs_manager()==0){
            throw new NonprivilegedUserException();
        } else {
            group.setName(name);
            group.setInfo(info);
            groupMapper.updateGroup(group);

        }
        return true;
    }

//    /**
//     * 团队修改人员
//     * @param userId
//     * @return
//     */
//    public boolean editMember(int userId) {
//        return false;
//    }

    /**
     * 查看团队信息
     * @param groupId
     * @return
     */
    public Group getGroupInfo(int groupId) throws NotExistedException {
        Group group = groupMapper.selectById(groupId);
        if(group==null){
            throw new NotExistedException();
        }else
            return group;
    }

    /**
     * 查看团队成员
     * @param groupId
     * @return
     */
    public List<GMember> getMemberList(int groupId) throws NotExistedException {
        List<GMember> gMembers = gMemberMapper.getGMemberByGroup(groupId);
        if (gMembers==null){
            throw new NotExistedException();
        } else
            return gMembers;
    }

    /**
     * 查看所属团队
     * @param member_Name
     * @return
     */
    public List<Group> getGroupList(String member_Name) throws NotExistedException {
        List<Group> groups = groupMapper.selectMyGroups(member_Name);
        if (groups==null){
            throw new NotExistedException();
        }else
            return groups;
    }


}

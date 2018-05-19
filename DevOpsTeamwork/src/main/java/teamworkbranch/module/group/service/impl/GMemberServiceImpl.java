package teamworkbranch.module.group.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamworkbranch.exception.ExistedException;
import teamworkbranch.exception.NonprivilegedUserException;
import teamworkbranch.exception.NotExistedException;
import teamworkbranch.module.group.dao.GMemberMapper;
import teamworkbranch.module.group.dao.GroupMapper;
import teamworkbranch.module.group.model.GMember;
import teamworkbranch.module.group.service.GMemberService;

/**
 * Created by caosh on 2018/4/20.
 */
@Service
@Transactional
public class GMemberServiceImpl implements GMemberService {

    @Autowired
    GroupMapper groupMapper;
    @Autowired
    GMemberMapper gMemberMapper;

    /**
     * 团队增加成员
     * @param groupId
     * @param userName
     * @param is_manager
     * @return
     */
    @Override
    public boolean addMember(int groupId,String userName,int is_manager,String memberName) throws ExistedException, NonprivilegedUserException {
        GMember gMember = gMemberMapper.selectById(groupId,userName);
        if(gMember!=null) {
            throw new ExistedException();
        }
        if (!isManager(groupId,memberName)) {
            throw new NonprivilegedUserException();
        } else {
            gMember = new GMember(groupId, userName, is_manager);
            gMemberMapper.insertGMember(gMember);
        }

        return true;
    }


    /**
     * 团队删除成员
     * @param userName
     * @param groupId
     * @return
     */
    @Override
    public boolean removeMember(int groupId,String userName,String memberName) throws NotExistedException, NonprivilegedUserException {
        GMember gMember = gMemberMapper.selectById(groupId,userName);
        if(gMember==null) {
            throw new NotExistedException();
        }
        if (!isManager(groupId,memberName)) {
            throw new NonprivilegedUserException();
        } else {
            gMemberMapper.deleteGMember(groupId, userName);
        }

        return true;
    }

    /**
     * 修改团队成员管理权限
     * @param groupId
     * @param userName
     * @param is_manager
     * @return
     */
    @Override
    public boolean editMember(int groupId,String userName,int is_manager,String memberName) throws NotExistedException, NonprivilegedUserException {
        GMember gMember = gMemberMapper.selectById(groupId,userName);
        if(gMember==null) {
            throw new NotExistedException();
        }
        if (!isManager(groupId,memberName)) {
            throw new NonprivilegedUserException();
        } else {
                gMemberMapper.editGMember(gMember);
        }

        return true;
    }

    /**
     * 成员退出团队
     * @param groupId
     * @param userName
     * @return
     * @throws NotExistedException
     */
    @Override
    public boolean leaveGroup(int groupId, String userName) throws NotExistedException {
        GMember gMember = gMemberMapper.selectById(groupId,userName);
        if(gMember==null) {
            throw new NotExistedException();
        } else{
            gMemberMapper.deleteGMember(groupId,userName);
        }
        return true;
    }

    private boolean isManager(int groupId,String memberName){
        GMember gMember = gMemberMapper.selectById(groupId,memberName);
        if(gMember.getIs_manager()==1)
            return true;
        else
            return false;
    }
}

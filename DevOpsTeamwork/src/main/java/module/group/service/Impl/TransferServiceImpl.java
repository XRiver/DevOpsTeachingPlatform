package module.group.service.Impl;

import module.model.Group;
import module.model.VO.GroupVO;
import module.group.service.TransferService;

/**
 * Created by caosh on 2018/4/12.
 */
public class TransferServiceImpl implements TransferService {


    public Group transfer_to_PO(GroupVO groupVO) {
        Group groupPO = new Group(groupVO.getGroupName(),
                groupVO.getInfo(),
                groupVO.getMemberList(),
                groupVO.getGroupId(),
                groupVO.getCreator(),
                groupVO.getManagerList(),
                groupVO.getUpdate_time(),
                groupVO.getCreate_time());
        return groupPO;
    }

    public GroupVO transfer_to_VO(Group groupPO) {
        GroupVO groupVO = new GroupVO(groupPO.getGroupName(),
                groupPO.getInfo(),
                groupPO.getMemberList(),
                groupPO.getGroupId(),
                groupPO.getCreator(),
                groupPO.getManagerList(),
                groupPO.getUpdate_time(),
                groupPO.getCreate_time());

        return groupVO;
    }
}

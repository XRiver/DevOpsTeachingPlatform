package teamworkbranch.module.group.service.impl;

import teamworkbranch.module.entity.PO.GroupPO;
import teamworkbranch.module.entity.VO.GroupVO;
import teamworkbranch.module.group.service.TransferService;

/**
 * Created by caosh on 2018/4/12.
 */
public class TransferServiceImpl implements TransferService {


    public GroupPO transfer_to_PO(GroupVO groupVO) {
        GroupPO groupPO = new GroupPO(groupVO.getGroupName(),
                groupVO.getInfo(),
                groupVO.getMemberList(),
                groupVO.getGroupId(),
                groupVO.getCreator(),
                groupVO.getManagerList(),
                groupVO.getUpdate_time(),
                groupVO.getCreate_time());
        return groupPO;
    }

    public GroupVO transfer_to_VO(GroupPO groupPO) {
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

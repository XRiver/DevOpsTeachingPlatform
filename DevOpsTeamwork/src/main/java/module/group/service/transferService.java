package module.group.service;

import module.model.Group;
import module.model.VO.GroupVO;

/**
 * Created by caosh on 2018/4/12.
 */
public interface TransferService {

    public Group transfer_to_PO(GroupVO groupVO);

    public GroupVO transfer_to_VO(Group groupPO);
}

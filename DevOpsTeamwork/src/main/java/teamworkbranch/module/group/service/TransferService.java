package teamworkbranch.module.group.service;

import teamworkbranch.module.entity.PO.GroupPO;
import teamworkbranch.module.entity.VO.GroupVO;

/**
 * Created by caosh on 2018/4/12.
 */
public interface TransferService {

    public GroupPO transfer_to_PO(GroupVO groupVO);

    public GroupVO transfer_to_VO(GroupPO groupPO);
}

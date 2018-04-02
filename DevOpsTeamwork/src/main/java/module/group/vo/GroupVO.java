package module.group.vo;

import module.user.vo.UserVO;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by caosh on 2018/4/1.
 */
public class GroupVO {
    private String groupName;
    private String info;//团队信息
    private ArrayList<UserVO> memberList;//团队成员
    private int groupId;//团队编号
    private UserVO creator;//团队创建者
    private ArrayList<UserVO> managerList;//团队管理者
    private Date create_time;//创建时间
    private Date update_time;//更新时间

}

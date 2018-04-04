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


    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ArrayList<UserVO> getMemberList() {
        return memberList;
    }

    public void setMemberList(ArrayList<UserVO> memberList) {
        this.memberList = memberList;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public UserVO getCreator() {
        return creator;
    }

    public void setCreator(UserVO creator) {
        this.creator = creator;
    }

    public ArrayList<UserVO> getManagerList() {
        return managerList;
    }

    public void setManagerList(ArrayList<UserVO> managerList) {
        this.managerList = managerList;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }


}

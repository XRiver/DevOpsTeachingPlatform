package edu.nju.model;

import java.util.List;

/**
 * Created by Administrator on 2018/4/1.
 */

//根据之前的设计，每一个用户可以同时出于不同的组中，每个组对应一个项目以及仓库。
// 那么在团队管理模块应该有一个“选择组”的功能（类似tower中的选择团队）并向其他模块提供接口，
// 其他模块根据当前用户所选择的组（项目）展示不同的内容。
public class Team {
    int teamID;

    Group group;

    Project project;

    Repository repository;

    List<GroupMember> groupMemberList;

    public Team() {
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public List<GroupMember> getGroupMemberList() {
        return groupMemberList;
    }

    public void setGroupMemberList(List<GroupMember> groupMemberList) {
        this.groupMemberList = groupMemberList;
    }
}

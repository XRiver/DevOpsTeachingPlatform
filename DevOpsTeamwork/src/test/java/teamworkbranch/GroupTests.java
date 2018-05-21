package teamworkbranch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import teamworkbranch.exception.NonprivilegedUserException;
import teamworkbranch.exception.NotExistedException;
import teamworkbranch.module.group.dao.GroupMapper;
import teamworkbranch.module.group.service.GMemberService;
import teamworkbranch.module.group.service.GroupService;

import java.util.ArrayList;

/**
 * Created by caosh on 2018/4/23.
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class GroupTests {
//    @Autowired
//    GroupService groupService;
//    @Autowired
//    GroupMapper groupMapper;
//    @Autowired
//    GMemberService gMemberService;
//
//    @Test
//    public void testCreate(){
//        ArrayList<String> memberList = new ArrayList<>();
//        memberList.add("user1");
//        groupService.createGroup("group3","group3","user1",memberList);
//    }
//
//    @Test
//    public void tesrGet() throws NotExistedException {
//        groupService.getGroupInfo(1);
//    }
//
//    @Test
//    public void tesrDelete1() throws NotExistedException, NonprivilegedUserException {
//        groupService.deleteGroup(1,"user1");
//    }
//
//    @Test
//    public void tesrDelete2() throws NotExistedException, NonprivilegedUserException {
//        groupService.deleteGroup(1,"user2");
//    }
//
//    @Test
//    public void testEdit() throws NotExistedException, NonprivilegedUserException {
//        groupService.editGroup("group2","newGroup2",2,"user1");
//    }
//
//    @Test
//    public void testGetMemberList() throws NotExistedException {
//        groupService.getMemberList(1);
//        System.out.println(groupService.getMemberList(1).size());
//    }
//
//    @Test
//    public void testGetMyGroups() throws NotExistedException {
//        groupService.getGroupList("user2");
//        System.out.println(groupService.getGroupList("user1").size());
//    }
//}

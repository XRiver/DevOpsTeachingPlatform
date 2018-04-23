package teamworkbranch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import teamworkbranch.module.group.dao.GroupMapper;
import teamworkbranch.module.group.service.GMemberService;
import teamworkbranch.module.group.service.GroupService;

import java.util.ArrayList;

/**
 * Created by caosh on 2018/4/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupTests {
    @Autowired
    GroupService groupService;
    @Autowired
    GroupMapper groupMapper;
    @Autowired
    GMemberService gMemberService;

    @Test
    public void testCreate(){
        ArrayList<String> memberList = new ArrayList<>();
        memberList.add("user1");
        groupService.createGroup("group3","group3","user1",memberList);
    }

    @Test
    public void tesrGet(){
        groupService.getGroupInfo(1);
    }
}

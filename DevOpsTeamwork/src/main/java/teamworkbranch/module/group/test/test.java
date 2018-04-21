package teamworkbranch.module.group.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import teamworkbranch.module.group.dao.GroupMapper;

/**
 * Created by caosh on 2018/4/21.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class test {
    @Autowired
    private GroupMapper groupMapper;

    @Test
    public void testInsert(){
//        Group group = new Group("group1","group","user1");
        Assert.assertEquals("group1",groupMapper.selectById(1).getName());
    }


}

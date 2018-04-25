package teamworkbranch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import teamworkbranch.module.user.dao.UserMapper;
import teamworkbranch.module.user.model.User;
import teamworkbranch.module.user.web.IdentificationController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {
	@Autowired
	UserMapper userMapper;

	@Autowired
	IdentificationController identificationController;

	@Test
    public void testInsert(){
		userMapper.insertUser(new User("user4","123456","USER1","141250065","xxx@yy.com",1));

	}
	@Test
	public void testUpdate(){
		userMapper.updateUser(new User("user4","123456","USER1","141250061","xxx@yy.com",1));

	}
	@Test
	public void testSelect(){
		User user=userMapper.selectByUserName("user4");
	}

	@Test
	public void testController(){
//		identificationController.signUp("user01","12345678",0,"xxx@yy.com","张三","141250000");
//		identificationController.login("user01","12345678");
	}





	@Test
	public void contextLoads() {
	}



}

package teamworkbranch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import teamworkbranch.exception.ExistedException;
import teamworkbranch.exception.NonprivilegedUserException;
import teamworkbranch.module.project.dao.ProjectMapper;
import teamworkbranch.module.project.model.Project;
import teamworkbranch.module.project.service.PManagerService;
import teamworkbranch.module.project.service.ProjectService;
import teamworkbranch.module.user.dao.UserMapper;
import teamworkbranch.module.user.model.User;
import teamworkbranch.module.user.service.IdentificationService;
import teamworkbranch.module.user.web.IdentificationController;
import teamworkbranch.module.user.web.UserController;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {
	@Autowired
	UserMapper userMapper;

	@Autowired
	IdentificationController identificationController;

	@Test
    public void testInsert(){
		userMapper.insertUser(new User("user4","123456","用户1","141250065","xxx@yy.com",1));

	}
	@Test
	public void testUpdate(){
		userMapper.updateUser(new User("user4","123456","用户1","141250061","xxx@yy.com",1));

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

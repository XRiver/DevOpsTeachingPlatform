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
import teamworkbranch.module.project.web.ProjectController;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectTests {
	@Autowired
	ProjectService projectService;
	@Autowired
	ProjectMapper projectMapper;
	@Autowired
    PManagerService pManagerService;
	@Autowired
    ProjectController projectController;
	@Test
    public void testCreate(){
		ArrayList<String> list=new ArrayList<>();
		list.add("user2");
		projectService.createWithGroup("project2","this is a project",list,2,"user2");
	}

	@Test
	public void testGet(){
    	Project project =projectService.getProjectInfo(1);
	}

	@Test
	public void testEdit(){
		try {
			projectService.editProject(6, "newProject", "change", "user2");
		}catch (NonprivilegedUserException e){
			e.getMessage();
		}
	}
	@Test
	public void testGetProject(){
		List<Project> list=projectMapper.getProjectsByUser("user1");
	}

	@Test
	public void testAuthority(){
        try {
            pManagerService.addManager(1,"user2","user1");
        } catch (NonprivilegedUserException e) {
            e.printStackTrace();
        } catch (ExistedException e) {
            e.printStackTrace();
        }

//        try {
//            pManagerService.deleteManager(1,"user2","user1");
//        } catch (NonprivilegedUserException e) {
//            e.printStackTrace();
//        }
    }
	@Test
	public void contextLoads() {
	    ArrayList<String> list=new ArrayList<>();
	    list.add("user3");
	    list.add("user4");
        projectController.createWithGroup("newProject","new project",list,1,"user3");

	}



}

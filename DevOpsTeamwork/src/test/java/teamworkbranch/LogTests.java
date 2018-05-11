package teamworkbranch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import teamworkbranch.exception.NonprivilegedUserException;
import teamworkbranch.exception.NotExistedException;
import teamworkbranch.module.log.model.Log;
import teamworkbranch.module.log.service.LogService;
import teamworkbranch.module.project.dao.ProjectMapper;
import teamworkbranch.module.project.service.PManagerService;
import teamworkbranch.module.project.service.ProjectService;
import teamworkbranch.module.project.web.ProjectController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caosh on 2018/4/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogTests {
    @Autowired
    ProjectService projectService;
    @Autowired
    ProjectMapper projectMapper;
    @Autowired
    PManagerService pManagerService;
    @Autowired
    ProjectController projectController;
    @Autowired
    LogService logService;

    @Test
    public void testCreate(){
        ArrayList<String> list=new ArrayList<>();
        list.add("user2");
        projectService.createWithGroup("project2","this is a project",list,2,"user2","jenkins");
    }

    @Test
    public void testGet() throws NotExistedException {
       Log log = logService.getLog(1);
    }

    @Test
    public void testGetLogByProjectId() throws NotExistedException {
        List<Log> logs = logService.getLogByProject(9);
    }

    @Test
    public void testDelete() throws NotExistedException, NonprivilegedUserException {
        logService.deleteLog(1,"user2");
        }

//        try {
//            pManagerService.deleteManager(1,"user2","user1");
//        } catch (NonprivilegedUserException e) {
//            e.printStackTrace();
//        }
//    }


}

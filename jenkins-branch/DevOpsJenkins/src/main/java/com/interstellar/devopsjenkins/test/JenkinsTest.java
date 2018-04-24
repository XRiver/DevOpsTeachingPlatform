package com.interstellar.devopsjenkins.test;

import com.interstellar.devopsjenkins.service.JenkinsService;
import com.offbytwo.jenkins.JenkinsServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JenkinsTest {
    @Autowired
    JenkinsServer jenkinsServer;
    @Autowired
    JenkinsService jenkinsService;

    @Test
    public void test() throws IOException {
        /*String url = "http://localhost:8080";
        JenkinsServer server;
        StringBuilder build = new StringBuilder();
        String name = "admin";
        String password = "4c33e952ce104b89b301b8dce14912f1";
        try {
            server = new JenkinsServer(new URI(url), name, password);
            InputStream in = new FileInputStream("C:/Jenkins/jobs/HostelWorld/config.xml");
            InputStreamReader read = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                build.append(lineTxt);
            }
            String jobname = "oopsaHostel";
            System.out.println(build.toString());
            server.createJob(jobname,build.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }*/
        /*try {
            System.out.println(jenkinsServer.getJob("new").getLastBuild().details().getResult());
            ;
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        jenkinsService.periodJob("sasa", 3);
    }
}

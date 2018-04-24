package com.interstellar.devopsjenkins.config;

import com.interstellar.devopsjenkins.util.jenkinsURL;
import com.offbytwo.jenkins.JenkinsServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
public class JenkinsServerConfig {

    @Bean
    public JenkinsServer getJenkinsServer() {
        JenkinsServer jenkinsServer = null;
        try {
            jenkinsServer = new JenkinsServer(new URI(jenkinsURL.getJenkins()), jenkinsURL.getName(), jenkinsURL.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jenkinsServer;
    }
}

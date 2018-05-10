package com.interstellar.devopsjenkins.config;

import com.interstellar.devopsjenkins.util.jenkinsURL;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
public class JenkinsServerConfig {

    @Bean
    public JenkinsServer getJenkinsServer() {
        JenkinsServer jenkinsServer = null;
        JenkinsHttpClient jenkinsHttpClient = null;
        try {
            jenkinsHttpClient = new JenkinsHttpClient(new URI(jenkinsURL.getJenkins()), jenkinsURL.getName(), jenkinsURL.getPassword());
            jenkinsServer = new JenkinsServer(jenkinsHttpClient);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jenkinsServer;
    }
}

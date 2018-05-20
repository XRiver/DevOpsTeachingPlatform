package com.Common;

import org.springframework.beans.factory.annotation.Value;

public class DefaultPath {
    static String gitUrl="139.219.66.203:8762";
    @Value("${home.path}")
    static String home;

    public static String getDefaultDir(){
            return "/tmp/project";
    }

    public static String getGit(){
        return  gitUrl;
    }

    public static String getHome(){

        return home;
    }
}

package com.Common;

import org.springframework.beans.factory.annotation.Value;

public class DefaultPath {
    @Value("${log.path}")
    static String path;


    public static String getDefaultDir(){
            return "/tmp/project";
    }

    public static String getHome(){
        return "/home/xinyu";
    }
}

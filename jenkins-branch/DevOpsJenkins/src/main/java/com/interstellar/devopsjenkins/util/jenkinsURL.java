package com.interstellar.devopsjenkins.util;

public class jenkinsURL {
    private static String jenkins = "http://localhost:8080";

    private static String name = "admin";
    private static String password = "4c33e952ce104b89b301b8dce14912f1";


    public static String getHome() {
        return jenkins;
    }

    public static String creatJob() {
        return jenkins + "/creatItem";
    }

    public static String getJenkins() {
        return jenkins;
    }

    public static String getName() {
        return name;
    }

    public static String getPassword() {
        return password;
    }
}

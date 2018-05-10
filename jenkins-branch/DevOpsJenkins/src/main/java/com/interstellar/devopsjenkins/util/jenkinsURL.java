package com.interstellar.devopsjenkins.util;

public class jenkinsURL {
    private static String jenkins = "http://localhost:8080";

    private static String name = "admin";
    private static String password = "qwe1996222";


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

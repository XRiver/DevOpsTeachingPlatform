package com.interstellar.devopsjenkins.util;

public class jenkinsURL {
    private static String jenkins = "http://localhost:8070";

    public static String getDevOpsgitlab() {
        return DevOpsgitlab;
    }

    public static String getGitLabToken() {
        return GitLabToken;
    }

    private static String DevOpsgitlab="http://localhost:8762";
    private static String GitLabToken="yseqMqqyo9mBEaiFzmf3";

    private static String name = "admin";
    private static String password = "qwe1996222";


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

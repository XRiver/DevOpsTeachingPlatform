package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ShellCommand {
    private static final String passwd="123qwe";


    public static void main(String[] args){
        //callSudoCommand("mkdir /tmp/project");

    }


    public static List<String> callShellCommands(List<String>  commands){

        if(commands.size()<1) {
            return null;
        }
        List<String> results=new ArrayList<String>();
        String command=commands.get(0);
        for(int i=1;i<commands.size();i++) {
            command+="&&"+commands.get(i);
        }
        try {
            Process process=Runtime.getRuntime().exec(new String[] {"/bin/sh","-c",command});
            int exitValue=process.waitFor();
            if(0!=exitValue) {
                System.out.println("fail to call shell "+command+exitValue);
            }else {
                BufferedReader br=new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line="";
                while((line=br.readLine())!=null) {
                    results.add(line);
                    System.out.println(line);
                }
                br.close();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return results;


    }


    public static boolean callSudoCommand(String command) {
        List<String> results=new ArrayList<String>();
        String sudoCmd = "echo \""+passwd+"\" | sudo -S ";
        try {
            Process process=Runtime.getRuntime().exec(new String[] {"/bin/bash","-c",command});
            int exitValue=process.waitFor();
            if(0!=exitValue) {
                System.out.println("fail to call shell "+command+exitValue);
                return false;
            }else {
                BufferedReader br=new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line="";
                while((line=br.readLine())!=null) {
                    results.add(line);
                    System.out.println(line);
                }
                br.close();
                return true;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static boolean clearDir(String path){
        return callSudoCommand("rm -rf"+path);

    }

}

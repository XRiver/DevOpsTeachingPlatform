package nju.wqy.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author WQY
 *
 */
public class ShellManager {

	public static void main(String[] args) {
		//callShellCommand("./etc/profile");
		callShellCommand("sh startSonar.sh");
		List<String> a=new ArrayList<String>();
		//a.add("cd /Users/Alisa/Desktop/rgp/ExamOnlineSOA-Exam");
		//a.add("sonar-scanner");
		 //a.add("ls -l");
		//callShellCommands(a);
		//callShellScript("startSonar.sh");
//		 Map<String, String> map = System.getenv();
//	        for(Iterator<String> itr = map.keySet().iterator();itr.hasNext();){
//	            String key = itr.next();
//	            System.out.println(key + "=" + map.get(key));
//	        }   
//		
	}

	public static List<String> callShellCommands(List<String> commands) {
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
			}else {//成功执行了shell脚本
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
	public static boolean callShellScript(String fileName) {
		//callShellCommand("#!/bin/bash -ilex");
		String cmd="/bin/sh "+"/Users/Alisa/Downloads/SpringBoot-Template-master/"+fileName;
		if(callShellCommand(cmd)) {
			return true;
		}
		return false;
	}

	public static boolean callShellCommand(String command) {
		List<String> results=new ArrayList<String>();
		String sudoCmd = "echo \"password\" | sudo -S ";  
		  
		try {
			//Process process=Runtime.getRuntime().exec(command);
			//Process process=Runtime.getRuntime().exec(new String[] {"/bin/bash","-c",sudoCmd + command}); 
			Process process=Runtime.getRuntime().exec(new String[] {"/bin/bash","-c",command}); 
			int exitValue=process.waitFor();
			if(0!=exitValue) {
				System.out.println("fail to call shell "+command+exitValue);	
				return false;
			}else {//成功执行了shell脚本
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
}

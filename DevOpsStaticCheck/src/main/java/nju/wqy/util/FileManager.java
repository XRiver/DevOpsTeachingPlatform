package nju.wqy.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author WQY
 *
 */
public class FileManager {
	public static void main(String[] args) {
		List<String> a=new ArrayList<String>();
		a.add("abcdefg");
		a.add("1234567");
		writeFile("test.properties",a);
	}

	/**
	 * 确保文件目录下有文件
	 * @param filePath
	 */
	public static void createFile(String filePath) {
		File file=new File(filePath);
		if(!file.exists()) {
			try {
				file.createNewFile();
				System.out.println("create file in"+filePath);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 写文件
	 * @param filePath
	 */
	public static void writeFile(String filePath,List<String> contents) {
		createFile(filePath);
		String lineSeparator=System.getProperty("line.separator");
		FileWriter fw = null ;
		try {
			fw=new FileWriter(filePath);
			for(int i=0;i<contents.size();i++) {
				fw.write(contents.get(i));
				fw.write(lineSeparator);
			}
			System.out.println("write file successfully");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}

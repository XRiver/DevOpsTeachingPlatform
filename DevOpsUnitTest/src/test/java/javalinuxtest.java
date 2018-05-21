import com.util.ScriptGenerate;

import java.io.*;

public class javalinuxtest {

    public static void main(String[] args) {
        String path="/home/xinyu/java/abcc";

        String out= ScriptGenerate.javashAll();
        javaTestAll();

    }

    public static void javaTestAll() {
        Runtime runtime = Runtime.getRuntime();
        String command="";
        String src="/home/xinyu/java/abcc";

        String path=""+src;
        String shPath=src+"/exectest.sh";
        File shfile = new File(path);
        if(!shfile.exists()){
            shfile.getParentFile().mkdirs();
        }
        try {
            shfile.createNewFile();
            FileWriter fw = new FileWriter(shfile, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(ScriptGenerate.javashAll());
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        command="cd "+path+" && sh ./exectest.sh ";
        String[] commands=new String[]{"/bin/sh","-c","cd "+path+" && sh ./exectest.sh "};

        String line = null;
        String out="";
        try {
            Process process = runtime.exec(commands);
            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(process.getInputStream()));

            while ((line = bufferedReader.readLine()) != null) {
                out=out+line + "\n";
            }
            process.waitFor();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(out);


    }

}

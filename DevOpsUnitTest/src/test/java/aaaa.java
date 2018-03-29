import java.io.*;

/**
 * Created by Administrator on 2018/3/21.
 */
public class aaaa {

    public static void aaa(String[] args){
        String path="C:\\java\\py\\test\\test.bat";
        String src="C:\\java\\py\\test";
        File file = new File(path);
        if(!file.exists()){
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(file, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("cmd /c  C: &&  cd C:\\java\\py\\test  && py -m pytest C:\\java\\py\\test\\py_test1.py"+" --junitxml=" + src + "\\report\\log.xml"+
            "\n exit");
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Runtime runtime = Runtime.getRuntime();
        String command="";
        command="cmd /c start "+path;
        Process process=null;

        String line = null;
        String out="";
        try {
            process=runtime.exec(command);
            InputStreamReader isr=new InputStreamReader(process.getInputStream());
            //用缓冲器读行
            BufferedReader br=new BufferedReader(isr);
            //直到读完为止
            while((line=br.readLine())!=null) {
                System.out.println(line);
            }

            process.waitFor();

        } catch (IOException e) {
            e.printStackTrace();
        }catch (InterruptedException e) {
           e.printStackTrace();
        }
        int i= process.exitValue();
        if (i == 0) {
            System.out.println("执行完成.");
        } else {
            System.out.println("执行失败.");
        }

        process.destroy();  //销毁子进程
        process = null;
    }
}

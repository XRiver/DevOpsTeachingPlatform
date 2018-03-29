import java.io.*;

/**
 * Created by Administrator on 2018/3/21.
 */
public class bbb {
    public static void bbb(String[] args){
        String path="C:\\java\\py\\test\\test.bat";


        Runtime runtime = Runtime.getRuntime();
        String command="";
        command="cmd /c C: &&cd C:\\java\\abcc && call mvn -Dtest=Hello2Test,Hello3Test test ";
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

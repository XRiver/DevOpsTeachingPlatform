import com.Entity.FaultInfo;
import com.Entity.Report;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/3/22.
 */
public class ccc {
    public static void ccc(String[] args){

        String path="C:\\java\\abcc";
        Report report=javaReport(path);
        System.out.println(report.getCase_num());
        System.out.println(report.getFail_num());

    }

    private static Report javaReport(String src){
        src+="\\target\\surefire-reports";
        File dir=new File(src);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Report report=new Report();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        report.setTime(sdf.format(d));

        int cases=0;
        int failures=0;
        int success=0;
        int error=0;

        if (!dir.isDirectory()) {
            System.out.println("not a dir");

            report.setError_info("diretory error");
        } else {
            // 内部匿名类，用来过滤文件类型
            File[] xmlList = dir.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    if (file.isFile() && file.getName().endsWith(".xml")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            for (File f:xmlList) {
                DocumentBuilder builder = null;
                try {
                    builder = factory.newDocumentBuilder();
                    Document document = builder.parse(f);
                    Element rootElement = document.getDocumentElement();
                    cases+=Integer.parseInt(rootElement.getAttribute("tests"));
                    failures+=Integer.parseInt(rootElement.getAttribute("failures"));
                    int failnum=Integer.parseInt(rootElement.getAttribute("failures"));
                    String name=rootElement.getAttribute("name");
                    error+=Integer.parseInt(rootElement.getAttribute("errors"));

                    if(failnum>0){
                        NodeList failList = rootElement.getElementsByTagName("failure");
                        for(int i=0;i<failnum;i++){
                            Element fail = (Element)failList.item(i);
                            FaultInfo faultInfo=new FaultInfo();
                            faultInfo.setCase_name(name);
                            String funcname=((Element)fail.getParentNode()).getAttribute("name");
                            faultInfo.setFunc_name(funcname);
                            System.out.println(funcname);

                            String type=fail.getAttribute("type");
                            if(fail.getAttribute("message")!=null){
                                type=type+"  "+fail.getAttribute("message");
                            }
                            System.out.println(type);
                            faultInfo.setType(type);
                            String value=fail.getFirstChild().getNodeValue();
                            System.out.println(value);
                            int line=0;
                            int start=value.indexOf(funcname);
                            int end=value.indexOf(")",start);
                            value=value.substring(start,end);
                            value=value.split(":")[1];
                            line=Integer.parseInt(value);
                            faultInfo.setLine(line);
                            report.addFault_info(faultInfo);

                            System.out.println(name+" "+funcname+" "+line+" "+type);

                        }



                    }

                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            success=cases-failures;
            report.setCase_num(cases);
            report.setFail_num(failures);
            report.setSucess_num(success);
            report.setError_info("");
            if(cases==0){
                report.setError_info("result do not exist");
            }

        }

        return report;
    }
}

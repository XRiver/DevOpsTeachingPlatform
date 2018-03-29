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
 * Created by Administrator on 2018/3/28.
 */
public class cXML {

    public static void main(String[] args) {
        String path="C:\\java\\c";

        Report report=cReport(path);
        System.out.println(report.getCase_num());
        System.out.println(report.getFail_num());
    }

    private static Report cReport(String src){  //需要dtd

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Report report=new Report();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        report.setTime(sdf.format(d));

        File log=null;
        File dir=new File(src);
        if (!dir.isDirectory()) {
            System.out.println("not a dir");

            report.setError_info("diretory error");
        } else {
            // 内部匿名类，用来过滤文件类型
            File[] xmlList = dir.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    if (file.isFile() && file.getName().endsWith("-Results.xml")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });

            log=xmlList[0];
        }

        if(log==null){
            report.setError_info("no file");
            return report;
        }

        int cases=0;
        int failures=0;
        int success=0;
        int error=0;

        DocumentBuilder builder = null;

        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(log);
            Element rootElement = document.getDocumentElement();

            NodeList sumList = rootElement.getElementsByTagName("CUNIT_RUN_SUMMARY_RECORD");
            Element caseSum = (Element)sumList.item(1);
            String temp=caseSum.getElementsByTagName("RUN").item(0).getFirstChild().getNodeValue().trim();
            System.out.println(temp);
            cases=Integer.parseInt(temp);
            temp=caseSum.getElementsByTagName("FAILED").item(0).getFirstChild().getNodeValue().trim();
            failures=Integer.parseInt(temp);

            if(failures>0){
                NodeList failList = rootElement.getElementsByTagName("CUNIT_RUN_TEST_FAILURE");
                for(int i=0;i<failList.getLength();i++){
                    Element fail = (Element)failList.item(i);
                    FaultInfo faultInfo=new FaultInfo();
                    String name=fail.getElementsByTagName("FILE_NAME").item(0).getFirstChild().getNodeValue().trim();
                    faultInfo.setCase_name(name);
                    String funcname=fail.getElementsByTagName("TEST_NAME").item(0).getFirstChild().getNodeValue().trim();
                    faultInfo.setFunc_name(funcname);
                    int line=Integer.parseInt(fail.getElementsByTagName("LINE_NUMBER").item(0).getFirstChild().getNodeValue().trim());
                    faultInfo.setLine(line);
                    String failure=fail.getElementsByTagName("CONDITION").item(0).getFirstChild().getNodeValue().trim();
                    if(failure!=null){
                        faultInfo.setType(failure);
                        report.addFault_info(faultInfo);

                        System.out.println(name+" "+funcname+" "+line+" "+failure);
                    }

                }

            }
            if(error>0){
                NodeList errorList = rootElement.getElementsByTagName("error");
                Element errorTemp=(Element) errorList.item(0);
                String errorInfo=errorTemp.getAttribute("message");
                errorInfo+="\n"+errorTemp.getFirstChild().getNodeValue();
                report.setError_info(errorInfo);
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        success=cases-failures;
        report.setCase_num(cases);
        report.setFail_num(failures);
        report.setSucess_num(success);
        report.setError_info("");
        if(cases==0) {
            report.setError_info("result do not exist");
        }

        return report;
    }
}

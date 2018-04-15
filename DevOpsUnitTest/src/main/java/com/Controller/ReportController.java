package com.Controller;

import com.DataVO.MyResponseData;
import com.DataVO.ReportVO;
import com.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2018/3/23.
 */
@RestController
public class ReportController {
    @Autowired
    ReportService reportService;

    @RequestMapping(value = "/report/create", method = RequestMethod.POST)
    public MyResponseData<Boolean> createReport(@RequestParam("report")ReportVO reportVO, @RequestParam("testId") long testId){
        reportService.createReport(reportVO,testId);
        return new MyResponseData<Boolean>("succeed", new String[]{"成功创建缺陷！"}, true);
    }

    @RequestMapping(value = "/report/delete", method = RequestMethod.POST)
    public MyResponseData<Boolean> deleteReport(@RequestParam("id") long id){
        reportService.deleteReport(id);
        return new MyResponseData<Boolean>("succeed", new String[]{"成功删除缺陷！"}, true);
    }

    @RequestMapping(value = "/report/get-by-test", method = RequestMethod.GET)
    public List<ReportVO> getReportByTest(@RequestParam("testId") long id){
        List<ReportVO> reports=reportService.getReportByTest(id);
        return reports;
    }

    @RequestMapping(value = "/report/get", method = RequestMethod.GET)
    public ReportVO getReportById(@RequestParam("id") long id){
        ReportVO report=reportService.getReportById(id);
        return report;
    }

    @RequestMapping(value = "/report/get-latest", method = RequestMethod.GET)
    public ReportVO getLatestReport(@RequestParam("testId") long id){
        List<ReportVO> reports=reportService.getReportByTest(id);
        ReportVO latest;
        latest=reports.get(reports.size()-1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (ReportVO temp:reports){
            try {
                if (sdf.parse(temp.getTime()).before(sdf.parse(latest.getTime()))){
                    latest=temp;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return latest;
    }

}

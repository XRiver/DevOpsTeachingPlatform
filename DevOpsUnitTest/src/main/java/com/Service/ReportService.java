package com.Service;

import com.DataVO.ReportVO;

import java.util.List;

/**
 * Created by Administrator on 2018/3/20.
 */
public interface ReportService {
    public boolean createReport(ReportVO reportVO,Long testId);

    public boolean deleteReport(Long id);

    public List<ReportVO> getReportByTest(Long testId);

    public ReportVO getReportById(Long id);
}

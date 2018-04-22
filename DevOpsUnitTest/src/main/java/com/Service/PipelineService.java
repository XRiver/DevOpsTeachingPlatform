package com.Service;

import com.DataVO.ReportVO;
import com.Entity.Report;

public interface PipelineService {
    public Report javaReport(String src);

    public Report pythonReport(String src);

    public Report cReport(String src);

    public ReportVO pipelineReport(String group,String project);
}

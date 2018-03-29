package com.Service;

import com.DataVO.ReportVO;

import java.util.List;

/**
 * Created by Administrator on 2018/3/20.
 */
public interface TestExecuteService {
    public ReportVO javaTestAll(Long testId,String username);

    public ReportVO javaTest(List<String> file,Long testId ,String username);

    public ReportVO pythonTest(List<String> file, Long testId ,String username);

    public ReportVO pythonTestAll(Long testId ,String username);

    public ReportVO cTest(List<String> file,Long testId ,String username);
}

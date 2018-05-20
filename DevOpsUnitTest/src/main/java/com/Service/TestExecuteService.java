package com.Service;

import com.DataVO.ReportVO;

import java.util.List;

/**
 * Created by Administrator on 2018/3/20.
 */
public interface TestExecuteService {
    public ReportVO javaTestAll(String path,Long testId,String username);

    public ReportVO javaTest(String path,List<String> file,Long testId ,String username);

    public ReportVO pythonTest(String path,List<String> file, Long testId ,String username);

    public ReportVO pythonTestAll(String path,Long testId ,String username);

    public ReportVO cTest(String path,List<String> file,Long testId ,String username);

    public ReportVO cTestAll(String path,Long testId ,String username);
}

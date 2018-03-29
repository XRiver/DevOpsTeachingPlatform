package com.DataVO;

import java.util.List;

/**
 * Created by Administrator on 2018/3/16.
 */
public class TestVO {
    private Long id;

    private String project_id;

    private String testId;

    private String name;

    private int perform_times;

    private String latest_time;

    private String latest_person;

    private String language;

    private String src;

    private String branch;

    private boolean isAuto;

    private List<TestCaseVO> test_case;

    private List<ReportVO> reports;

    public TestVO() {
    }

    public TestVO(Long id, String project_id, String testId, String name, int perform_times, String latest_time, String latest_person, String language, String src, String branch, boolean isAuto, List<TestCaseVO> test_case, List<ReportVO> reports) {
        this.id = id;
        this.project_id = project_id;
        this.testId = testId;
        this.name = name;
        this.perform_times = perform_times;
        this.latest_time = latest_time;
        this.latest_person = latest_person;
        this.language = language;
        this.src = src;
        this.branch = branch;
        this.isAuto = isAuto;
        this.test_case = test_case;
        this.reports = reports;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPerform_times() {
        return perform_times;
    }

    public void setPerform_times(int perform_times) {
        this.perform_times = perform_times;
    }

    public String getLatest_time() {
        return latest_time;
    }

    public void setLatest_time(String latest_time) {
        this.latest_time = latest_time;
    }

    public String getLatest_person() {
        return latest_person;
    }

    public void setLatest_person(String latest_person) {
        this.latest_person = latest_person;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public boolean isAuto() {
        return isAuto;
    }

    public void setAuto(boolean auto) {
        isAuto = auto;
    }

    public List<TestCaseVO> getTest_case() {
        return test_case;
    }

    public void setTest_case(List<TestCaseVO> test_case) {
        this.test_case = test_case;
    }

    public List<ReportVO> getReports() {
        return reports;
    }

    public void setReports(List<ReportVO> reports) {
        this.reports = reports;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}

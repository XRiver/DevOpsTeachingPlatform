package com.Entity;
import com.DataVO.ReportVO;
import com.DataVO.TestCaseVO;
import com.DataVO.TestVO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/16.
 */
@Entity
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "testEntity", cascade = {CascadeType.ALL})
    private List<TestCase> test_case= new ArrayList<TestCase>();

    @OneToMany(mappedBy = "testEntity", cascade = {CascadeType.ALL})
    private List<Report> reports= new ArrayList<Report>();

    public TestEntity() {
    }

    public TestEntity(String project_id, String testId, String name, int perform_times, String latest_time, String latest_person, String language, String src, String branch,boolean isAuto, List<TestCase> test_case, List<Report> reports) {
        this.project_id = project_id;
        this.testId = testId;
        this.name = name;
        this.perform_times = perform_times;
        this.latest_time = latest_time;
        this.latest_person = latest_person;
        this.language = language;
        this.src = src;
        this.branch=branch;
        this.isAuto = isAuto;
        this.test_case = test_case;
        this.reports = reports;
    }

    public TestEntity(TestVO testVO) {
        if(testVO.getId()!=null&&testVO.getId()!=0){
            this.id=testVO.getId();
        }
        this.project_id = testVO.getProject_id();
        this.testId = testVO.getTestId();
        this.name = testVO.getName();
        this.perform_times = testVO.getPerform_times();
        this.latest_time = testVO.getLatest_time();
        this.latest_person = testVO.getLatest_person();
        this.language = testVO.getLanguage();
        this.src = testVO.getSrc();
        this.branch=testVO.getBranch();
        this.isAuto = testVO.isAuto();
        this.test_case = test_case;
        this.reports = reports;
        if(testVO.getTest_case()!=null){
            for(TestCaseVO vo1:testVO.getTest_case()){
                addTest_case(new TestCase(vo1));
            }
        }
        if(testVO.getReports()!=null){
            for(ReportVO vo2:testVO.getReports()){
                addReports(new Report(vo2));
            }
        }
    }

    public TestVO toTestVO(){
        List<TestCaseVO> test_caseList=new ArrayList<TestCaseVO>();
        List<ReportVO> reportList=new ArrayList<ReportVO>();
        for(TestCase testCase:this.test_case){
            test_caseList.add(testCase.toTestCaseVO());
        }
        for(Report report:this.reports){
            reportList.add(report.toReportVO());
        }
        return new TestVO(id,project_id,testId,name,perform_times,latest_time,latest_person,language,src,branch,isAuto,test_caseList,reportList);
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

    public List<TestCase> getTest_case() {
        return test_case;
    }

    public void setTest_case(List<TestCase> test_case) {
        this.test_case = test_case;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void addTest_case(TestCase test_case) {
        test_case.setTestEntity(this);
        this.test_case.add(test_case);
    }

    public void addReports(Report report) {
        report.setTestEntity(this);
        this.reports.add(report);
    }

}

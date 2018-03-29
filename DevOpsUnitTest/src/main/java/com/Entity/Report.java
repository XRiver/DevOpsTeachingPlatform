package com.Entity;
import com.DataVO.FaultInfoVO;
import com.DataVO.ReportVO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/16.
 */
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String time;

    private int case_num;

    private int sucess_num;

    private int fail_num;

    private String error_info;


    @ManyToOne
    @JoinColumn(name = "test_id")
    private TestEntity testEntity;

    @OneToMany(mappedBy = "report", cascade = {CascadeType.ALL})
    private List<FaultInfo> fault_info= new ArrayList<FaultInfo>();

    public Report() {
    }

    public Report(String time, int case_num, int sucess_num, int fail_num, String error_info, TestEntity testEntity, List<FaultInfo> fault_info) {
        this.time = time;
        this.case_num = case_num;
        this.sucess_num = sucess_num;
        this.fail_num = fail_num;
        this.error_info = error_info;
        this.testEntity = testEntity;
        this.fault_info = fault_info;
    }

    public Report(ReportVO reportVO) {
        if(reportVO.getId()!=null&&reportVO.getId()!=0){
            this.id=reportVO.getId();
        }
        this.time = reportVO.getTime();
        this.case_num = reportVO.getCase_num();
        this.sucess_num = reportVO.getSucess_num();
        this.fail_num = reportVO.getFail_num();
        this.error_info = reportVO.getError_info();
        if(reportVO.getFault_info()!=null){
            for(FaultInfoVO vo:reportVO.getFault_info()){
                addFault_info(new FaultInfo(vo));
            }
        }
    }

    public ReportVO toReportVO(){
        List<FaultInfoVO> fault_infoList=new ArrayList<FaultInfoVO>();
        for(FaultInfo faultInfo:this.fault_info){
            fault_infoList.add(faultInfo.toFaultInfoVO());
        }

        return new ReportVO(id,time,case_num,sucess_num,fail_num,error_info,fault_infoList);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCase_num() {
        return case_num;
    }

    public void setCase_num(int case_num) {
        this.case_num = case_num;
    }

    public int getSucess_num() {
        return sucess_num;
    }

    public void setSucess_num(int sucess_num) {
        this.sucess_num = sucess_num;
    }

    public int getFail_num() {
        return fail_num;
    }

    public void setFail_num(int fail_num) {
        this.fail_num = fail_num;
    }

    public String getError_info() {
        return error_info;
    }

    public void setError_info(String error_info) {
        this.error_info = error_info;
    }

    public TestEntity getTestEntity() {
        return testEntity;
    }

    public void setTestEntity(TestEntity testEntity) {
        this.testEntity = testEntity;
    }

    public List<FaultInfo> getFault_info() {
        return fault_info;
    }

    public void setFault_info(List<FaultInfo> fault_info) {
        this.fault_info = fault_info;
    }

    public void addFault_info(FaultInfo fault_info) {
        fault_info.setReport(this);
        this.fault_info.add(fault_info);
    }

}

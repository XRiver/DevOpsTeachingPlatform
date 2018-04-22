package com.Entity;

import com.DataVO.FaultInfoVO;

import javax.persistence.*;

/**
 * Created by Administrator on 2018/3/16.
 */
@Entity
public class FaultInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;

    private String case_name;

    private String func_name;

    private int line;

    @Lob
    private String type;

    public FaultInfo() {
    }

    public FaultInfo(Report report, String case_name, String func_name, int line, String type) {
        this.report = report;
        this.case_name = case_name;
        this.func_name = func_name;
        this.line = line;
        this.type = type;
    }

    public FaultInfo(FaultInfoVO faultInfoVO) {
        if(faultInfoVO.getId()!=null&&faultInfoVO.getId()!=0){
            this.id=faultInfoVO.getId();
        }
        this.case_name = faultInfoVO.getCase_name();
        this.func_name = faultInfoVO.getFunc_name();
        this.line = faultInfoVO.getLine();
        this.type = faultInfoVO.getType();
    }

    public FaultInfoVO toFaultInfoVO(){
        return new FaultInfoVO(id,case_name,func_name,line,type);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public String getCase_name() {
        return case_name;
    }

    public void setCase_name(String case_name) {
        this.case_name = case_name;
    }

    public String getFunc_name() {
        return func_name;
    }

    public void setFunc_name(String func_name) {
        this.func_name = func_name;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

package com.DataVO;

import java.util.List;

/**
 * Created by Administrator on 2018/3/16.
 */
public class ReportVO {
    private Long id;

    private String time;

    private int case_num;

    private int sucess_num;

    private int fail_num;

    private String error_info;

    private List<FaultInfoVO> fault_info;

    public ReportVO() {
    }

    public ReportVO(Long id, String time, int case_num, int sucess_num, int fail_num, String error_info, List<FaultInfoVO> fault_info) {
        this.id = id;
        this.time = time;
        this.case_num = case_num;
        this.sucess_num = sucess_num;
        this.fail_num = fail_num;
        this.error_info = error_info;
        this.fault_info = fault_info;
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

    public List<FaultInfoVO> getFault_info() {
        return fault_info;
    }

    public void setFault_info(List<FaultInfoVO> fault_info) {
        this.fault_info = fault_info;
    }
}

package com.interstellar.devopsjenkins.util;

public class ResultMessage<T> {
    private boolean success;
    private String information;
    private T t;

    public ResultMessage(boolean success, String information, T t) {
        this.success = success;
        this.information = information;
        this.t = t;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}

package com.interstellar.devopsjenkins.vo;

public class BuildVO {
    private int number;
    private String url;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BuildVO(int number, String url) {
        this.number = number;
        this.url = url;
    }

    public BuildVO() {
    }
}

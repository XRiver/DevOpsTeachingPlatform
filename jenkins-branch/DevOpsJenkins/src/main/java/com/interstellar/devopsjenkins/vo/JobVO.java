package com.interstellar.devopsjenkins.vo;

public class JobVO {
    private String name;
    private String url;
    private String color;

    public JobVO(String name, String url, String color) {
        this.name = name;
        this.url = url;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

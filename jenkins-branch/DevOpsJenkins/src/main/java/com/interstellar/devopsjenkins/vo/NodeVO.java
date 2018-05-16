package com.interstellar.devopsjenkins.vo;

public class NodeVO {
    private String id;
    private String type;

    public NodeVO(String id, String result, String startTime, String state, String displayName, String type) {
        this.id = id;

        this.type = type;
    }

    public NodeVO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

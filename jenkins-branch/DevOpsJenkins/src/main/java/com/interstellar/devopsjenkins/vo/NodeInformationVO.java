package com.interstellar.devopsjenkins.vo;

import java.util.List;

public class NodeInformationVO {
    private String id;
    private String result;
    private String startTime;
    private String state;
    private String displayName;
    private String type;
    private String input;
    private String causeOfBlockage;
    private String displayDescription;
    private List<NodeVO> edges;

    public NodeInformationVO(String id, String result, String startTime, String state, String displayName, String type, String input, String causeOfBlockage, String displayDescription, List<NodeVO> edges) {
        this.id = id;
        this.result = result;
        this.startTime = startTime;
        this.state = state;
        this.displayName = displayName;
        this.type = type;
        this.input=input;
        this.causeOfBlockage=causeOfBlockage;
        this.displayDescription=displayDescription;
        this.edges = edges;
    }

    public NodeInformationVO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getCauseOfBlockage() {
        return causeOfBlockage;
    }

    public void setCauseOfBlockage(String causeOfBlockage) {
        this.causeOfBlockage = causeOfBlockage;
    }

    public String getDisplayDescription() {
        return displayDescription;
    }

    public void setDisplayDescription(String displayDescription) {
        this.displayDescription = displayDescription;
    }

    public List<NodeVO> getEdges() {
        return edges;
    }

    public void setEdges(List<NodeVO> edges) {
        this.edges = edges;
    }
}

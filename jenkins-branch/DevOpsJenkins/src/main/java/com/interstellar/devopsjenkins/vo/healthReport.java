package com.interstellar.devopsjenkins.vo;

public class healthReport {
    private String description;
    private int score;

    public healthReport() {
    }

    public healthReport(String description,int score) {
        this.description = description;
        this.score=score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

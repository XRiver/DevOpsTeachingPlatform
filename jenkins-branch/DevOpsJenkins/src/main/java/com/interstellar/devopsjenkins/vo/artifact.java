package com.interstellar.devopsjenkins.vo;

public class artifact {
    private String displayPath;
    private String fileName;
    private String relativePath;

    public artifact(String displayPath, String fileName, String relativePath) {
        this.displayPath = displayPath;
        this.fileName = fileName;
        this.relativePath = relativePath;
    }

    public artifact() {
    }

    public String getDisplayPath() {
        return displayPath;
    }

    public void setDisplayPath(String displayPath) {
        this.displayPath = displayPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }
}

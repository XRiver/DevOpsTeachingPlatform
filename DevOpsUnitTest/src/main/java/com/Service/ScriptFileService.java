package com.Service;

public interface ScriptFileService {
    public boolean uploadScript(String path,long testId);

    public boolean pipelineScript(String group,String project);
}

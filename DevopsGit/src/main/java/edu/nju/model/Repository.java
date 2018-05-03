package edu.nju.model;

/**
 * Created by Administrator on 2018/3/25.
 */
public class Repository {

    int repositoryID;

    int groupID;

    String url;

    String info;

    public  Repository(){

    }
    public Repository(int repositoryID, int groupID, String url, String info) {
        this.repositoryID = repositoryID;
        this.groupID = groupID;
        this.url = url;
        this.info = info;
    }

    public int getRepositoryID() {
        return repositoryID;
    }

    public void setRepositoryID(int repositoryID) {
        this.repositoryID = repositoryID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

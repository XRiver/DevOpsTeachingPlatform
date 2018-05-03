package edu.nju.model;

/**
 * Created by Administrator on 2018/4/1.
 */
public class Project {
    String id;
    String description;
    String ssh_url_to_repo;
    String http_url_to_repo;
    String web_url;
    String name;
//    "commit_count": 37,
//            "storage_size": 1038090,
//            "repository_size": 1038090,
//            "lfs_objects_size": 0,
//            "job_artifacts_size": 0
    String commit_count;
    String repository_size;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSsh_url_to_repo() {
        return ssh_url_to_repo;
    }

    public void setSsh_url_to_repo(String ssh_url_to_repo) {
        this.ssh_url_to_repo = ssh_url_to_repo;
    }

    public String getHttp_url_to_repo() {
        return http_url_to_repo;
    }

    public void setHttp_url_to_repo(String http_url_to_repo) {
        this.http_url_to_repo = http_url_to_repo;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommit_count() {
        return commit_count;
    }

    public void setCommit_count(String commit_count) {
        this.commit_count = commit_count;
    }

    public String getRepository_size() {
        return repository_size;
    }

    public void setRepository_size(String repository_size) {
        this.repository_size = repository_size;
    }
}

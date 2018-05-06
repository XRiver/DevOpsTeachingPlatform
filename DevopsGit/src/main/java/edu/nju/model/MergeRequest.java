package edu.nju.model;

/**
 * Created by Administrator on 2018/4/14.
 */
public class MergeRequest {

    String iid;
    String id;
    String target_branch;
    String source_branch;
    String title;
    String created_at;
    String author_username;
    String description;

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTarget_branch() {
        return target_branch;
    }

    public void setTarget_branch(String target_branch) {
        this.target_branch = target_branch;
    }

    public String getSource_branch() {
        return source_branch;
    }

    public void setSource_branch(String source_branch) {
        this.source_branch = source_branch;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getAuthor_username() {
        return author_username;
    }

    public void setAuthor_username(String author_username) {
        this.author_username = author_username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

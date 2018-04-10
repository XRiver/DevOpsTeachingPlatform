package edu.nju.model;

/**
 * Created by Administrator on 2018/3/31.
 */
public class SSHKey {
    int key_id;
    String title;
    String key;
    String create_at;

    public SSHKey() {
    }

    public int getKey_id() {
        return key_id;
    }

    public void setKey_id(int key_id) {
        this.key_id = key_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }
}

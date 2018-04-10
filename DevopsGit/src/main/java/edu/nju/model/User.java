package edu.nju.model;

/**
 * Created by Administrator on 2018/3/25.
 */
public class User {

    String username;

    String password;

    String email;

    String name;

    int id;

    public User(){

    }
    public User(String username, String password, String email, String name, int id) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

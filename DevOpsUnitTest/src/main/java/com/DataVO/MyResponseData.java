package com.DataVO;

/**
 * Created by Administrator on 2018/3/25.
 */
public class MyResponseData <T>{
    private String status;

    private String[] messages;

    private T data;

    public MyResponseData() {
    }

    public MyResponseData(String status, String[] messages, T data) {
        this.status = status;
        this.messages = messages;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getMessages() {
        return messages;
    }

    public void setMessages(String[] messages) {
        this.messages = messages;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

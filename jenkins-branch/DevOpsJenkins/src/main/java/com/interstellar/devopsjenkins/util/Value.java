package com.interstellar.devopsjenkins.util;

public class Value {

    private Long id;
    private String quote;

    public Value() {}

    public Long getId() {
        return id;
    }

    public String getQuote() {
        return quote;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "Value{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                '}';
    }

}
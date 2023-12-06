package com.example.backend.classes;

public abstract class form {
    private final String from;
    private final String to;
    private final String content;

    public form(String from, String to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }
}

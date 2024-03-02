package com.example.backend.classes;

public abstract class Form {
    private final String senderName;
    private final String content;

    public Form(String senderName, String content) {
        this.senderName = senderName;
        this.content = content;
    }

    public String getSenderName() {
        return senderName;
    }
    public String getContent() {
        return content;
    }
}

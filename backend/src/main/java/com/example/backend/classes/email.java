package com.example.backend.classes;

public class email extends form {
    private final String name;
    public email(String name, String from, String to, String content) {
        super(from, to, content);
        this.name = name;
    }
}

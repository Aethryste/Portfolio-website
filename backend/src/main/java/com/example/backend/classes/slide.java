package com.example.backend.classes;

public class slide extends file {
    private final long year;
    private final String title;
    private final String body;

    public slide(long year, String title, String body, String fileName, String fileType, String fileContent) {
        super(fileName, fileType, fileContent);
        this.year = year;
        this.title = title;
        this.body = body;
    }

    public long getYear() {
        return year;
    }
    public String getTitle() {
        return title;
    }
    public String getBody() {
        return body;
    }
}

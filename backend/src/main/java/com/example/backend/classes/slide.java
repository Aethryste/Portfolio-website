package com.example.backend.classes;

public class slide {

    private final long year;
    private final String title;
    private final String body;
    private final String imageType;
    private final String imageContent;

    public slide(long year, String title, String body, String imageType, String imageContent) {
        this.year = year;
        this.title = title;
        this.body = body;
        this.imageType = imageType;
        this.imageContent = imageContent;
    }

    // Getters
    public long getYear() {
        return year;
    }
    public String getTitle() {
        return title;
    }
    public String getBody() {
        return body;
    }
    public String getImageType() {
        return imageType;
    }
    public String getImageContent() {
        return imageContent;
    }
}

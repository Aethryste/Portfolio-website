package com.example.backend.classes;

public class slide {

    private final long year;
    private final String title;
    private final String body;
    private final String image;

    public slide(long year, String title, String body, String image) {
        this.year = year;
        this.title = title;
        this.body = body;
        this.image = image;
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
    public String getImage() {
        return image;
    }
}

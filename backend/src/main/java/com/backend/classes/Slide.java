package com.backend.classes;

import lombok.Getter;

@Getter
public class Slide extends File {
    private final long year;
    private final String title;
    private final String body;

    public Slide(long year, String title, String body, String fileName, String fileType, String fileContent) {
        super(fileName, fileType, fileContent);
        this.year = year;
        this.title = title;
        this.body = body;
    }
}

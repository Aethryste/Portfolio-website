package com.backend.classes;

public abstract class file {
    private final String fileName;
    private final String fileType;
    private final String fileContent;

    public file(String fileName, String fileType, String fileContent) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileContent = fileContent;
    }

    public String getName() {
        return fileName;
    }
    public String getType() {
        return fileType;
    }
    public String getContent() {
        return fileContent;
    }
}

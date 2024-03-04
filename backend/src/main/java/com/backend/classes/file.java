package com.backend.classes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class file {
    private final String fileName;
    private final String fileType;
    private final String fileContent;

    public file(String fileName, String fileType, String fileContent) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileContent = fileContent;
    }
}

package com.backend.classes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class File {
    private final String name;
    private final String type;
    private final String content;

    public File(String name, String type, String content) {
        this.name = name;
        this.type = type;
        this.content = content;
    }
}

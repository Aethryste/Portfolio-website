package com.backend.classes;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class imageEditor {

    public imageEditor() {}

    public String convertToBase64(String FilePath) {
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(new File(FilePath));
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            System.out.println("convertTo() - "+ e );
            return null;
        }
    }
    public String getType(String FilePath) {
        try {
            return FilenameUtils.getExtension(FilePath);
        } catch (Exception e) {
            System.out.println("getType() - "+ e );
            return null;
        }
    }
    public String getName(String FilePath) {
        try {
            return FilenameUtils.getName(FilePath);
        } catch (Exception e) {
            System.out.println("getName() - "+ e );
            return null;
        }
    }
}

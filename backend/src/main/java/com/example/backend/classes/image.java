package com.example.backend.classes;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class image {

    public image() {
    }

    public String convertToBase64(String FilePath) {
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(new File(FilePath));
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            System.out.println("convertTo() - File not found.");
            return null;
        }
    }
    public String getType(String FilePath) {
        try {
            return FilenameUtils.getExtension(FilePath);
        } catch (Exception e) {
            System.out.println("getType() - File not found.");
            return null;
        }


    }
}

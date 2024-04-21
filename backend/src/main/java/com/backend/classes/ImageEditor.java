package com.backend.classes;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@Component
public class ImageEditor {

    public String convertToBase64(String resourcePath) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(resourcePath);
            if (inputStream == null) {
                System.out.println("Error loading image: Resource not found: " + resourcePath);
                return null;
            }
            byte[] fileContent = inputStream.readAllBytes();
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            System.out.println("Error loading image: " + e.getMessage());
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
            System.out.println("getName() - " + e);
            return null;
        }
    }
}

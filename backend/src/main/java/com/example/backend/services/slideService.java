package com.example.backend.services;

import com.example.backend.classes.slide;
import com.example.backend.classes.imageEditor;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public class slideService {

    private static final imageEditor imageEditor = new imageEditor();
    // TODO: replace with database query, maybe mongo?
    private static ArrayList<slide> allSlides = new ArrayList<>(5);

    public slideService() {
    }

    public ArrayList<slide> getAllSlides() {
        if (allSlides.isEmpty()) {
            this.buildSlides();
        }
        return allSlides;
    }
    private void buildSlides() {
        // TODO: Insert file-path once to create instance, current setup is unnecessary.
        allSlides.add(new slide(
                2020,
                "All earlier experiences.",
                "I've got about 5 years of working experience before my decision to career-switch to IT " +
                        "took place, most of this experience is related to Graphic Design, Game Development, and " +
                        "providing moving/transport services. More details can be found on my LinkedIn.",
                imageEditor.getName("src/main/resources/static/slideshow-images/2020_laptop.jpg"),
                imageEditor.getType("src/main/resources/static/slideshow-images/2020_laptop.jpg"),
                imageEditor.convertToBase64("src/main/resources/static/slideshow-images/2020_laptop.jpg"))
        );
        allSlides.add(new slide(
                2021,
                "The start of it all.",
                "During 2021 I successfully completed a starter course on Python through Udemy, completing " +
                        "this course sparked my passion for programming. Not long after I decided to properly " +
                        "career-switch to IT by starting my Bachelor's degree at AUAS in Amsterdam.",
                imageEditor.getName("src/main/resources/static/slideshow-images/2021_amsterdam.jpg"),
                imageEditor.getType("src/main/resources/static/slideshow-images/2021_amsterdam.jpg"),
                imageEditor.convertToBase64("src/main/resources/static/slideshow-images/2021_amsterdam.jpg"))
        );
        allSlides.add(new slide(
                2022,
                "My first IT occupations.",
                "Shortly after starting my Bachelor I joined \"Newskool\", a secondment company specialised " +
                        "in providing, ambitious, starting IT-professionals to businesses in need. Through " +
                        "Newskool I gained my first three IT service/operations occupations, which provided me " +
                        "two consecutive years of professional experience working in large corporations and " +
                        "their digital infrastructures.",
                imageEditor.getName("src/main/resources/static/slideshow-images/2022_office.jpg"),
                imageEditor.getType("src/main/resources/static/slideshow-images/2022_office.jpg"),
                imageEditor.convertToBase64("src/main/resources/static/slideshow-images/2022_office.jpg"))
        );
        allSlides.add(new slide(
                2023,
                "The next step.",
                "Now, having built up professional experience working in IT-operations and having completed " +
                        "about half of my Bachelor, it's time for me to land my first Software Engineering / " +
                        "Developer occupation. This will be the next big step in my career, which i'm heavily " +
                        "looking forward to!",
                imageEditor.getName("src/main/resources/static/slideshow-images/2023_code.jpg"),
                imageEditor.getType("src/main/resources/static/slideshow-images/2023_code.jpg"),
                imageEditor.convertToBase64("src/main/resources/static/slideshow-images/2023_code.jpg"))
        );
    }
}

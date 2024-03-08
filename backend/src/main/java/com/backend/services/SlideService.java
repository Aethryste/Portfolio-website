package com.backend.services;

import com.backend.classes.ImageEditor;
import com.backend.classes.Slide;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public class SlideService {

    private static final ImageEditor imageEditor = new ImageEditor();
    private static final ArrayList<Slide> ALL_SLIDES = new ArrayList<>(5);
    private static final String pathRoot = "src/main/resources/static/slideshow-images/";

    public SlideService() {}

    public ArrayList<Slide> getAllSlides() {
        if (ALL_SLIDES.isEmpty()) {
            this.buildSlides();
        }
        return ALL_SLIDES;
    }
    private void buildSlides() {
        ALL_SLIDES.add(new Slide(
                2020,
                "All earlier experiences.",
                "I've got about 5 years of working experience before my decision to career-switch to IT " +
                        "took place, most of this experience is related to Graphic Design, Game Development, and " +
                        "providing moving/transport services. More details can be found on my LinkedIn.",
                imageEditor.getName(pathRoot+"2020_laptop.jpg"),
                imageEditor.getType(pathRoot+"2020_laptop.jpg"),
                imageEditor.convertToBase64(pathRoot+"2020_laptop.jpg"))
        );
        ALL_SLIDES.add(new Slide(
                2021,
                "The start of it all.",
                "During 2021 I successfully completed a starter course on Python through Udemy, completing " +
                        "this course sparked my passion for programming. Not long after I decided to properly " +
                        "career-switch to IT by starting my Bachelor's degree at AUAS in Amsterdam.",
                imageEditor.getName(pathRoot+"2021_amsterdam.jpg"),
                imageEditor.getType(pathRoot+"2021_amsterdam.jpg"),
                imageEditor.convertToBase64(pathRoot+"2021_amsterdam.jpg"))
        );
        ALL_SLIDES.add(new Slide(
                2022,
                "My first IT occupations.",
                "Shortly after starting my Bachelor I joined \"Newskool\", a secondment company specialised " +
                        "in providing, ambitious, starting IT-professionals to businesses in need. Through " +
                        "Newskool I gained my first three IT service/operations occupations, which provided me " +
                        "two consecutive years of professional experience working in large corporations and " +
                        "their digital infrastructures.",
                imageEditor.getName(pathRoot+"2022_office.jpg"),
                imageEditor.getType(pathRoot+"2022_office.jpg"),
                imageEditor.convertToBase64(pathRoot+"2022_office.jpg"))
        );
        ALL_SLIDES.add(new Slide(
                2023,
                "The next step.",
                "Now, having built up professional experience working in IT-operations and having completed " +
                        "about half of my Bachelor, it's time for me to land my first Software Engineering / " +
                        "Developer occupation. This will be the next big step in my career, which i'm heavily " +
                        "looking forward to!",
                imageEditor.getName(pathRoot+"2023_code.jpg"),
                imageEditor.getType(pathRoot+"2023_code.jpg"),
                imageEditor.convertToBase64(pathRoot+"2023_code.jpg"))
        );
    }
}

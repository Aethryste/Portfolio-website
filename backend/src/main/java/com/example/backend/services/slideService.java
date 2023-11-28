package com.example.backend.services;

import com.example.backend.classes.slide;
import com.example.backend.classes.image;
import org.springframework.stereotype.Repository;

@Repository
public class slideService {

    private static final image image = new image();

    public slideService() {
    }

    public slide getSlide(int year) {
        if (year == 2020) {
            return new slide(
                    year,
                    "All earlier experiences.",
                    "I've got about 5 years of working experience before my decision to career-switch to IT " +
                            "took place, most of this experience is related to Graphic Design, Game Development, and " +
                            "providing moving/transport services. More details can be found on my LinkedIn.",
                    image.getType("src/main/resources/static/slideshow-images/2021_amsterdam.jpg"),
                    image.convertToBase64("src/main/resources/static/slideshow-images/2021_amsterdam.jpg"));
        }
        if (year == 2021) {
            return new slide(
                    year,
                    "The start of it all.",
                    "During 2021 I successfully completed a starter course on Python through Udemy, completing " +
                            "this course sparked my passion for programming. Not long after I decided to properly " +
                            "career-switch to IT by starting my Bachelor's degree at AUAS.",
                    image.getType("src/main/resources/static/slideshow-images/2021_amsterdam.jpg"),
                    image.convertToBase64("src/main/resources/static/slideshow-images/2021_amsterdam.jpg"));
        }
        if (year == 2022) {
            return new slide(
                    year,
                    "My first IT occupations.",
                    "Shortly after starting my Bachelor I joined \"Newskool\", a secondment company specialised " +
                            "in providing, ambitious, starting IT-professionals to businesses in need. Through " +
                            "Newskool I gained my first three IT service/operations occupations, which provided me " +
                            "two consecutive years of professional experience working in large corporations and " +
                            "their digital infrastructures.",
                    image.getType("src/main/resources/static/slideshow-images/2021_amsterdam.jpg"),
                    image.convertToBase64("src/main/resources/static/slideshow-images/2021_amsterdam.jpg"));
        }
        if (year == 2023) {
            return new slide(
                    year,
                    "The next step.",
                    "Now, having built up professional experience working in IT-operations and having completed " +
                            "about half of my Bachelor, it's time for me to land my first Software Engineering / " +
                            "Developer occupation. This will be the next big step in my career, which i'm heavily " +
                            "looking forward to!",
                    image.getType("src/main/resources/static/slideshow-images/2021_amsterdam.jpg"),
                    image.convertToBase64("src/main/resources/static/slideshow-images/2021_amsterdam.jpg"));
        }
        else { return null; }
    }

}

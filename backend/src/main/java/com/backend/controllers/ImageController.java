package com.backend.controllers;

import com.backend.classes.Slide;
import com.backend.configurations.SecurityConfig;
import com.backend.services.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@CrossOrigin(origins = SecurityConfig.ALLOWED_ORIGIN, maxAge = SecurityConfig.MAX_AGE)
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private SlideService slideService;

    @GetMapping("/timeline")
    public ArrayList<Slide> slideServiceGetSlide() {
        try {
            return slideService.getAllSlides();
        }
        catch (Exception e) {
            System.out.println("slideServiceGetSlide() - "+ e );
            return null;
        }
    }
}

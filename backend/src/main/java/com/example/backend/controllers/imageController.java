package com.example.backend.controllers;

import com.example.backend.classes.slide;
import com.example.backend.configurations.securityConfig;
import com.example.backend.services.slideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@CrossOrigin(origins = securityConfig.ALLOWED_ORIGIN, maxAge = securityConfig.MAX_AGE)
@RequestMapping("/images")
public class imageController {

    @Autowired
    private slideService slideService;

    @GetMapping("/timeline")
    public ArrayList<slide> slideServiceGetSlide() {
        try {
            return slideService.getAllSlides();
        }
        catch (Exception e) {
            System.out.println("slideServiceGetSlide() - "+ e );
            return null;
        }
    }
}

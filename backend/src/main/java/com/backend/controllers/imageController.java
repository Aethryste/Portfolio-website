package com.backend.controllers;

import com.backend.classes.slide;
import com.backend.configurations.securityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@CrossOrigin(origins = securityConfig.ALLOWED_ORIGIN, maxAge = securityConfig.MAX_AGE)
@RequestMapping("/images")
public class imageController {

    @Autowired
    private com.backend.services.slideService slideService;

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

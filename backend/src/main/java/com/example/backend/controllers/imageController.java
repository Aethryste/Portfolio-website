package com.example.backend.controllers;

import com.example.backend.classes.slide;
import com.example.backend.services.slideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")
public class imageController {

    @Autowired
    private slideService slideService;

    @GetMapping("/slides/{year}")
    public slide slideServiceGetSlide(@PathVariable String year) {
        try {
            return slideService.getSlide(Integer.parseInt(year));
        }
        catch (Exception e) {
            System.out.println("slideServiceGetSlide() - "+ e );
            return null;
        }
    }

}

package com.example.backend.controllers;

import com.example.backend.classes.slide;
import com.example.backend.services.slideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "localhost", maxAge = 3600)
@RequestMapping("/contact")
public class contactController {

    @Autowired
    private com.example.backend.services.contactService contactService;

    @PostMapping("/form")
    public void sendFormToEmail(@RequestBody String payload) {
        try {
            System.out.println(payload);
            contactService.sendEmail(contactService.createEmail(payload));
        }
        catch (Exception e) {
            System.out.println("sendFormToEmail() - "+ e );
        }
    }
}

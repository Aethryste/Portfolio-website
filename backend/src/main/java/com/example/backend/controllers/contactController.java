package com.example.backend.controllers;

import com.example.backend.configs.securityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = securityConfig.ALLOWED_ORIGIN, maxAge = securityConfig.MAX_AGE)
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

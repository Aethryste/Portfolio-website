package com.example.backend.controllers;

import com.example.backend.classes.Email;
import com.example.backend.configs.securityConfig;
import com.example.backend.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = securityConfig.ALLOWED_ORIGIN, maxAge = securityConfig.MAX_AGE)
@RequestMapping("/contact")
public class contactController {

    private final ContactService contactService;

    @Autowired
    public contactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/form")
    public void sendFormToEmail(@RequestBody String payload, HttpServletRequest request) {
        try {
//            String ipAddress = request.getRemoteAddr();

            //TODO: implement throttling logic here.
//            System.out.println("Sender IP Address: " + ipAddress);

            ContactService.sendEmail(ContactService.createEmail(payload));
        }
        catch (Exception e) {
            System.out.println("sendFormToEmail() - " + e );
            //TODO: Handle exceptions appropriately.
        }
    }
}

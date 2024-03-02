package com.example.backend.controllers;
import com.example.backend.classes.EmailDetails;
import com.example.backend.services.EmailServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired private EmailServiceImpl emailService;

    @PostMapping("/sendMail")
    public String sendMail(@RequestBody String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        EmailDetails details = mapper.readValue(json, EmailDetails.class);
        return emailService.sendSimpleMail(details);
    }
}

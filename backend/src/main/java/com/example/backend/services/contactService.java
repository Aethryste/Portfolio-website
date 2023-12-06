package com.example.backend.services;

import com.example.backend.classes.email;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class contactService {
    public email createEmail(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, email.class);
    }
    public void sendEmail(email emailObj) {};
}

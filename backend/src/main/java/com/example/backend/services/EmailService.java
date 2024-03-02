package com.example.backend.services;
import com.example.backend.classes.EmailDetails;

public interface EmailService {
    String sendSimpleMail(EmailDetails details);
}
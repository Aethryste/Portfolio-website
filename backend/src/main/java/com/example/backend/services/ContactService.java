package com.example.backend.services;

import com.example.backend.classes.Email;
import com.example.backend.configs.EmailConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Repository
public class ContactService {
    public static Email createEmail(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Email emailObj = mapper.readValue(json, Email.class);
        System.out.println(
                "\nEmailObj created:\n"
                        +emailObj.getSenderAddress()+"\n"
                        +emailObj.getSenderName()+"\n"
                        +emailObj.getContent()
        );
        return emailObj;
    }

    public static void sendEmail(Email emailObj) {
        System.out.println("\nsendEmail triggered, received: "+emailObj+"\n");

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", EmailConfig.getHost());
        properties.put("mail.smtp.port", EmailConfig.getPort());

        System.out.println("\nProperties set:\n");
        System.out.println("Keys: "+properties.keys()+"\n");
        System.out.println("Values: "+properties.values()+"\n");

        // Set up the session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EmailConfig.getUsername(), EmailConfig.getPassword());
            }
        });
        System.out.println("\nSession created: "+session+"\n");

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);
            // Set the sender email address
            message.setFrom(new InternetAddress("contact@richardalgra.com"));
            // Set the recipient email address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailObj.getRecipient()));
            // Set the email subject
            message.setSubject(emailObj.getSubject());
            // Set the email content
            message.setText(emailObj.getContent());

            // Send the email
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            System.out.println("Error sending email: " + e.getMessage());
            // Handle the exception appropriately
        }
    }
}
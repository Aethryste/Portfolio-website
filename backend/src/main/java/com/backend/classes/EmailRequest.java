package com.backend.classes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmailRequest {

    private String fromEmail;
    private String fromName;
    private String body;

    public EmailRequest(String to, String subject, String body) {
        this.fromEmail = to;
        this.fromName = subject;
        this.body = body;
    }
}

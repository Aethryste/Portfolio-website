package com.example.backend.classes;
import com.example.backend.configs.EmailConfig;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Email extends Form {
    private final String senderAddress;

    @JsonCreator
    public Email(@JsonProperty("senderAddress") String senderAddress,
                 @JsonProperty("senderName") String senderName,
                 @JsonProperty("content") String content) {
        super(senderName, content);
        this.senderAddress = senderAddress;
    }

    public String getSenderAddress() {
        return senderAddress;
    }
    public String getRecipient() {
        return EmailConfig.getUsername();
    }
    public String getSubject() {
        return "TempEmailSubject";
    }
}
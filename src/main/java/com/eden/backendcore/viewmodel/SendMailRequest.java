package com.eden.backendcore.viewmodel;

import lombok.Data;

@Data
public class SendMailRequest {

    private String sender;
    private String email;
    private String subject;
    private String content;
}

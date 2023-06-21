package com.eden.backendcore.service;

import com.eden.backendcore.viewmodel.SendMailRequest;

public interface MailService {

    Object sendEmail(SendMailRequest request);
}

package com.example.fuelmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private TwilioService twilioService;

    public void sendSms(String phoneNumber, String message) {
        twilioService.sendSms(phoneNumber, message);
    }
}

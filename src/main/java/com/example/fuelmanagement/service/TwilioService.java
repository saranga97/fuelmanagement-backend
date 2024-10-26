package com.example.fuelmanagement.service;

import com.example.fuelmanagement.model.FUEL_TYPE;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    @Value("${twilio.account_sid}")
    private String accountSid;

    @Value("${twilio.auth_token}")
    private String authToken;

    @Value("${twilio.phone_number}")
    private String twilioPhoneNumber;

    @PostConstruct
    public void initTwilio() {
        // Initialize Twilio after the values have been injected
        Twilio.init(accountSid, authToken);
    }

//    public void sendSms(String toPhoneNumber, String message) {
//        Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber(fromPhoneNumber), message)
//                .create();
//    }


    private void sendSMS(String phoneNumber, double liters, FUEL_TYPE fuelType) {
        if (!phoneNumber.startsWith("+")) {
            phoneNumber = "+94" + phoneNumber.substring(1);
        }

        Message.creator(
                new com.twilio.type.PhoneNumber(phoneNumber),
                new com.twilio.type.PhoneNumber(twilioPhoneNumber),
                String.format("You pumped %.2f liters of %s", liters, fuelType)
        ).create();
    }

}

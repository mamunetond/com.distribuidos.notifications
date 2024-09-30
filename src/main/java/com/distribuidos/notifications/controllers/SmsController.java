package com.distribuidos.notifications.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.distribuidos.notifications.services.NotifService;

@RestController
@RequestMapping("/api/notifications")
public class SmsController {

    @Autowired
    private NotifService notifService;

    @PostMapping("/send-sms")
    public String enviarSms(
    @RequestParam String toPhoneNumber,
    @RequestParam String body) {
    notifService.sendSms(toPhoneNumber, body);
    return "SMS successfully sent to" + toPhoneNumber;
}
    
}

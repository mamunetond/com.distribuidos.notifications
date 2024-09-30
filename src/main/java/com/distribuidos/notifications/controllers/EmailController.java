package com.distribuidos.notifications.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.distribuidos.notifications.services.NotifService;


@RestController
@RequestMapping("/api/notifications")
public class EmailController {

    @Autowired
    private NotifService notifService;

    @PostMapping("/send-email")
    public String sendEmail(
    @RequestParam String to,
    @RequestParam String subject,
    @RequestParam String body) {
    notifService.sendEmail(to, subject, body);
    return "Email successfully sent to" + to;
}
}
    

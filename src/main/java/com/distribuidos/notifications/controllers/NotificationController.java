package com.distribuidos.notifications.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.distribuidos.notifications.facades.NotificationFacade;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationFacade notificationFacade;

    // Endpoint to send email notification
    @PostMapping("/email")
    public String sendEmailNotification(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String body) {
        notificationFacade.sendEmailNotification(to, subject, body);
        return "Notificación por correo enviada.";
    }

    // Endpoint to send SMS notification
    @PostMapping("/sms")
    public String sendSmsNotification(
            @RequestParam String toPhoneNumber,
            @RequestParam String body) {
        notificationFacade.sendSmsNotification(toPhoneNumber, body);
        return "Notificación por SMS enviada.";
    }
}

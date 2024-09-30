package com.distribuidos.notifications.facades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.distribuidos.notifications.services.NotifService;

@Component
public class NotificationFacade {

    @Autowired
    private NotifService notifService;

    // Method to send a notification by mail
    public void sendEmailNotification(String to, String subject, String body) {
        notifService.sendEmail(to, subject, body);
        sendToCentralizer("EMAIL", to, subject, body);
    }

    // Method to send an SMS notification
    public void sendSmsNotification(String toPhoneNumber, String body) {
        notifService.sendSms(toPhoneNumber, body);
        sendToCentralizer("SMS", toPhoneNumber, null, body);
    }

    // Simulation of sending the notification to the centralize
    private void sendToCentralizer(String notificationType, String recipient, String subject, String body) {
        // Here you simulate the registration in the centralizer or send it to an external service
        System.out.println("Centralizador - Tipo de notificación: " + notificationType);
        System.out.println("Centralizador - Destinatario: " + recipient);
        if (subject != null) {
            System.out.println("Centralizador - Asunto: " + subject);
        }
        System.out.println("Centralizador - Contenido: " + body);
    }
}

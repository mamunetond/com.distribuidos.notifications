package com.distribuidos.notifications.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.distribuidos.notifications.exceptions.InvalidInputException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@Service
public class NotifService {
    
    @Autowired
    private JavaMailSender mailSender;

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String fromPhoneNumber;

    //Method to send emails
    public void sendEmail(String to, String subject, String body) {
        if (!isValidEmail(to)) {
            throw new InvalidInputException("Correo electrónico no válido: " + to);
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("mmunetondurango@gmail.com");
        mailSender.send(message);
    }

    //Initialized Twilio once when the service is created
    @jakarta.annotation.PostConstruct
    private void initTwilio(){
        Twilio.init(accountSid, authToken);
}
    // Method to send SMS
    public void sendSms(String toPhoneNumber, String body) {
        if (!isValidPhoneNumber(toPhoneNumber)) {
            throw new InvalidInputException("Número de teléfono no válido: " + toPhoneNumber);
        }

        Message message = Message.creator(
            new PhoneNumber(toPhoneNumber),
            new PhoneNumber(fromPhoneNumber),
            body).create();
    }

    //Method Notify Document Transfer
    public void notifyDocumentTransfer(String documentId, String userEmail, String userPhoneNumber) {
        String message = "Se realizó la transferencia del documento con Id: " + documentId;
    
        // Send mail
        sendEmail(userEmail, "Transferencia de Documento", message);
    
        // Send SMS
        sendSms(userPhoneNumber, message);
    }
    
    //Method Notify Citizen Transfer
    public void notifyCitizenTransfer(String citizenId, String userEmail, String userPhoneNumber) {
        String message = "Se realizó la transferencia del ciudadano con el número de identificación: " + citizenId;
    
        // Send mail
        sendEmail(userEmail, "Transferencia de Ciudadano", message);
    
        // Send SMS
        sendSms(userPhoneNumber, message);
    }
    
    // Custom validation for phone numbers using regular expressions
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("\\+?[0-9]{10,15}");
    }

    // Custom validation for emails (optional, if @Email not used)
    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

}

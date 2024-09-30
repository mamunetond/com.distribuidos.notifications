package com.distribuidos.notifications.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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
    //public void sendEmail(String to, String subject, String body) {
      //  SimpleMailMessage message = new SimpleMailMessage();
        //message.setTo(to);
        //message.setSubject(subject);
        //message.setText(body);
        //message.setFrom("mmunetondurango@gmail.com");
        //mailSender.send(message);
    //}

    //Method to initialize Twilio
    //private void initTwilio(){
      //  Twilio.init(accountSid, accountSid);
    //}

    //public void sendSms(String toPhoneNumber, String body) {
      //  initTwilio();

        //Message message = Message.creator(
          //  new PhoneNumber(toPhoneNumber),
            //new PhoneNumber(fromPhoneNumber),
            //body) //SMS cotent
            //.create();
    //}
}

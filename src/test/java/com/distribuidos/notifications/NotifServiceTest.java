package com.distribuidos.notifications;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.distribuidos.notifications.exceptions.InvalidInputException;
import com.distribuidos.notifications.services.NotifService;
import com.twilio.Twilio;

public class NotifServiceTest {

    @Mock
    private JavaMailSender mailSender; // Simulate sending emails

    @InjectMocks
    private NotifService notifService; // The service we want to try

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize the mocks
    }

    // Test to send valid emails
    @Test
    public void testSendEmailValid() {
        String to = "example@example.com";
        String subject = "Test Subject";
        String body = "Test Body";

        // Execute the method we want to test
        notifService.sendEmail(to, subject, body);

        // Verify that the email was sent correctly
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    // Test to send invalid emails
    @Test
    public void testSendEmailInvalid() {
        String to = "invalid-email"; // invalid email
        String subject = "Test Subject";
        String body = "Test Body";

        // Check that the exception is thrown when the email is invalid
        try {
            notifService.sendEmail(to, subject, body);
        } catch (InvalidInputException e) {
            assert(e.getMessage().contains("Invalid email"));
        }

        // Verify that the email has NOT been sent
        verify(mailSender, times(0)).send(any(SimpleMailMessage.class));
    }

    // Test to send a valid SMS
    @Test
    public void testSendSmsValid() {
        String toPhoneNumber = "+1234567890";
        String body = "Test SMS Body";

        // Simulate Twilio behavior
        Twilio.init("testSid", "testToken"); // Simulate Twilio initialization
        notifService.sendSms(toPhoneNumber, body);


    }

    // Test to send an invalid SMS
    @Test
    public void testSendSmsInvalid() {
        String toPhoneNumber = "123"; // Invalid phone number
        String body = "Test SMS Body";

        // Check that the exception is thrown when the number is invalid
        try {
            notifService.sendSms(toPhoneNumber, body);
        } catch (InvalidInputException e) {
            assert(e.getMessage().contains("Invalid phone number"));
        }
    }
}

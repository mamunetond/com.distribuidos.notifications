package com.distribuidos.notifications;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.distribuidos.notifications.exceptions.InvalidInputException;
import com.distribuidos.notifications.services.NotifService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Message.class) // Prepare the Message class for mocking static methods
public class NotifServiceTest {

    @Mock
    private JavaMailSender mailSender; // Simulate sending emails

    @InjectMocks
    private NotifService notifService; // The service we want to test

    @Mock
    private MessageCreator messageCreator; // Mock the MessageCreator from Twilio

    @Mock
    private Message message; // Mock the Twilio Message response

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this); // Initialize the mocks
        mockStatic(Message.class); // Mock static methods in Message class
    }

    @Test
    public void testSendEmailValid() {
        String to = "mmunetondurango@gmail.com";
        String subject = "Test Subject";
        String body = "Test Body";

        // Execute the method we want to test
        notifService.sendEmail(to, subject, body);

        // Verify that the email was sent correctly
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    public void testSendEmailInvalid() {
        String to = "invalid-email"; // invalid email
        String subject = "Test Subject";
        String body = "Test Body";

        // Check that the exception is thrown when the email is invalid
        try {
            notifService.sendEmail(to, subject, body);
        } catch (InvalidInputException e) {
            assert (e.getMessage().contains("Invalid email"));
        }

        // Verify that the email has NOT been sent
        verify(mailSender, times(0)).send(any(SimpleMailMessage.class));
    }

    @Test
    public void testSendSmsValid() {
        String toPhoneNumber = "+573166399954";
        String body = "Test SMS Body";

        // Mock the static method Message.creator
        when(Message.creator(any(PhoneNumber.class), any(PhoneNumber.class), anyString()))
            .thenReturn(messageCreator);

        // Mock the behavior of the message creation
        when(messageCreator.create()).thenReturn(message);

        // Call the method to test
        notifService.sendSms(toPhoneNumber, body);

        // Verify that the Message.creator was called with the correct parameters
        verify(messageCreator, times(1)).create();
    }

    @Test
    public void testSendSmsInvalid() {
        String toPhoneNumber = "123"; // Invalid phone number
        String body = "Test SMS Body";

        // Check that the exception is thrown when the number is invalid
        try {
            notifService.sendSms(toPhoneNumber, body);
        } catch (InvalidInputException e) {
            assert (e.getMessage().contains("Invalid phone number"));
        }

        // Verify that the Message.creator was not called because of the invalid phone number
        verify(messageCreator, times(0)).create();
    }
}

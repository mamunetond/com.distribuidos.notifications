package com.distribuidos.notifications;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

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
import com.distribuidos.notifications.models.Citizen;
import com.distribuidos.notifications.models.Document;
import com.distribuidos.notifications.repositories.CitizenRepository;
import com.distribuidos.notifications.repositories.DocumentRepository;
import com.distribuidos.notifications.services.NotifService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Message.class) // Prepare the Message class for mocking static methods
public class NotifServiceTest {

    @Mock
    private JavaMailSender mailSender; // Simulate sending emails

    @Mock
    private CitizenRepository citizenRepository; // Mock repository for Citizen

    @Mock
    private DocumentRepository documentRepository; // Mock repository for Document

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
    public void testNotifyDocumentTransferWithExistingDocument() {
        String documentId = "12345";
        String userEmail = "mmunetondurango@gmail.com";
        String userPhoneNumber = "+573166399954";
        String expectedMessage = "Se realizó la transferencia del documento con Id: " + documentId;

        // Mock the behavior of documentRepository to return a valid document
        Document mockDocument = new Document();
        mockDocument.setDocumentId(documentId);
        when(documentRepository.findByDocumentId(documentId)).thenReturn(Optional.of(mockDocument));

        // Mock the behavior of sendEmail and sendSms
        doNothing().when(mailSender).send(any(SimpleMailMessage.class));
        when(Message.creator(any(PhoneNumber.class), any(PhoneNumber.class), anyString()))
            .thenReturn(messageCreator);
        when(messageCreator.create()).thenReturn(message);

        // Call the method to test
        notifService.notifyDocumentTransfer(documentId, userEmail, userPhoneNumber);

        // Verify that sendEmail was called with correct parameters
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));

        // Verify that sendSms was called with the correct parameters
        verify(messageCreator, times(1)).create();
    }

    @Test(expected = InvalidInputException.class)
    public void testNotifyDocumentTransferWithNonExistentDocument() {
        String documentId = "12345";
        String userEmail = "mmunetondurango@gmail.com";
        String userPhoneNumber = "+573166399954";

        // Mock the behavior of documentRepository to return an empty result
        when(documentRepository.findByDocumentId(documentId)).thenReturn(Optional.empty());

        // Call the method to test, expecting an exception
        notifService.notifyDocumentTransfer(documentId, userEmail, userPhoneNumber);

        // Verify that sendEmail and sendSms are NOT called
        verify(mailSender, times(0)).send(any(SimpleMailMessage.class));
        verify(messageCreator, times(0)).create();
    }

    @Test
    public void testNotifyCitizenTransferWithExistingCitizen() {
        String citizenId = "987654321";
        String userEmail = "citizen@example.com";
        String userPhoneNumber = "+573166399954";
        String expectedMessage = "Se realizó la transferencia del ciudadano con el número de identificación: " + citizenId;

        // Mock the behavior of citizenRepository to return a valid citizen
        Citizen mockCitizen = new Citizen();
        mockCitizen.setCitizenId(citizenId);
        when(citizenRepository.findByCitizenId(citizenId)).thenReturn(Optional.of(mockCitizen));

        // Mock the behavior of sendEmail and sendSms
        doNothing().when(mailSender).send(any(SimpleMailMessage.class));
        when(Message.creator(any(PhoneNumber.class), any(PhoneNumber.class), anyString()))
            .thenReturn(messageCreator);
        when(messageCreator.create()).thenReturn(message);

        // Call the method to test
        notifService.notifyCitizenTransfer(citizenId, userEmail, userPhoneNumber);

        // Verify that sendEmail was called with correct parameters
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));

        // Verify that sendSms was called with the correct parameters
        verify(messageCreator, times(1)).create();
    }

    @Test(expected = InvalidInputException.class)
    public void testNotifyCitizenTransferWithNonExistentCitizen() {
        String citizenId = "987654321";
        String userEmail = "citizen@example.com";
        String userPhoneNumber = "+573166399954";

        // Mock the behavior of citizenRepository to return an empty result
        when(citizenRepository.findByCitizenId(citizenId)).thenReturn(Optional.empty());

        // Call the method to test, expecting an exception
        notifService.notifyCitizenTransfer(citizenId, userEmail, userPhoneNumber);

        // Verify that sendEmail and sendSms are NOT called
        verify(mailSender, times(0)).send(any(SimpleMailMessage.class));
        verify(messageCreator, times(0)).create();
    }
}

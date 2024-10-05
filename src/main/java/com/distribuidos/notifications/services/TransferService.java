package com.distribuidos.notifications.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distribuidos.notifications.exceptions.InvalidInputException;
import com.distribuidos.notifications.models.Citizen;
import com.distribuidos.notifications.models.Document;
import com.distribuidos.notifications.repositories.CitizenRepository;
import com.distribuidos.notifications.repositories.DocumentRepository;

@Service
public class TransferService {

    @Autowired
    private NotifService notifService;

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private DocumentRepository documentRepository;

    // Método para transferir un ciudadano con validación
    public void transferCitizen(String citizenId, String userEmail, String userPhoneNumber) {
        // Verificar si el ciudadano existe
        Optional<Citizen> citizenOpt = citizenRepository.findByCitizenId(citizenId);
        if (!citizenOpt.isPresent()) {
            throw new InvalidInputException("El ciudadano con el número de identificación " + citizenId + " no existe.");
        }

        // Aquí podrías agregar la lógica adicional de transferencia de ciudadano.
        // Si la transferencia es exitosa, enviamos las notificaciones
        notifService.notifyCitizenTransfer(citizenId, userEmail, userPhoneNumber);
    }

    // Método para transferir un documento con validación
    public void transferDocument(String documentId, String userEmail, String userPhoneNumber) {
        // Verificar si el documento existe
        Optional<Document> documentOpt = documentRepository.findByDocumentId(documentId);
        if (!documentOpt.isPresent()) {
            throw new InvalidInputException("El documento con Id " + documentId + " no existe.");
        }

        // Aquí podrías agregar la lógica adicional de transferencia de documento.
        // Si la transferencia es exitosa, enviamos las notificaciones
        notifService.notifyDocumentTransfer(documentId, userEmail, userPhoneNumber);
    }
}

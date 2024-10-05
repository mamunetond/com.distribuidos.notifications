package com.distribuidos.notifications.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.distribuidos.notifications.services.TransferService;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping("/citizen")
    public void transferCitizen(@RequestBody TransferRequest request) {
        transferService.transferCitizen(request.getCitizenId(), request.getUserEmail(), request.getUserPhoneNumber());
    }

    @PostMapping("/document")
    public void transferDocument(@RequestBody TransferRequest request) {
        transferService.transferDocument(request.getDocumentId(), request.getUserEmail(), request.getUserPhoneNumber());
    }
}

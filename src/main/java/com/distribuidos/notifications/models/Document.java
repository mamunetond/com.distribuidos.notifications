package com.distribuidos.notifications.models;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;

@Entity
public class Document {

    @Id
    private String documentId;
    private String title;

    // Getters y setters
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

package com.distribuidos.notifications.models;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;

@Entity
public class Citizen {

    @Id
    private String citizenId;
    private String name;

    // Getters y setters
    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

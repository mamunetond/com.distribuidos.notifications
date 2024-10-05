package com.distribuidos.notifications.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.distribuidos.notifications.models.Citizen;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, String> {
    Optional<Citizen> findByCitizenId(String citizenId);
}

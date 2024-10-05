package com.distribuidos.notifications.repositories;

import com.distribuidos.notifications.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, String> {
    Optional<Document> findByDocumentId(String documentId);
}

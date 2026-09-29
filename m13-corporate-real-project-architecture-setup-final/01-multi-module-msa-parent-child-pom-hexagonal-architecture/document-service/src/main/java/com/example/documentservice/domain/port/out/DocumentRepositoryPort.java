package com.example.documentservice.domain.port.out;

import com.example.documentservice.domain.model.Document;

import java.util.List;
import java.util.Optional;

public interface DocumentRepositoryPort {

    Document save(Document document);

    Optional<Document> findById(Long id);

    List<Document> findAll();
}

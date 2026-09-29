package com.example.documentservice.application.service;

import com.example.documentservice.domain.model.Document;
import com.example.documentservice.domain.port.in.UploadDocumentUseCase;
import com.example.documentservice.domain.port.out.DocumentRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UploadDocumentService implements UploadDocumentUseCase {

    private final DocumentRepositoryPort documentRepositoryPort;

    public UploadDocumentService(DocumentRepositoryPort documentRepositoryPort) {
        this.documentRepositoryPort = documentRepositoryPort;
    }

    @Override
    public Document upload(UploadCommand command) {
        Document document = new Document(
                null,
                command.fileName(),
                command.contentType(),
                command.content().length,
                command.content(),
                Instant.now());
        return documentRepositoryPort.save(document);
    }
}

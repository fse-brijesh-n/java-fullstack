package com.example.documentservice.application.service;

import com.example.documentservice.domain.model.Document;
import com.example.documentservice.domain.port.in.DownloadDocumentUseCase;
import com.example.documentservice.domain.port.in.ListDocumentsUseCase;
import com.example.documentservice.domain.port.out.DocumentRepositoryPort;
import com.example.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentQueryService implements DownloadDocumentUseCase, ListDocumentsUseCase {

    private final DocumentRepositoryPort documentRepositoryPort;

    public DocumentQueryService(DocumentRepositoryPort documentRepositoryPort) {
        this.documentRepositoryPort = documentRepositoryPort;
    }

    @Override
    public Document download(Long id) {
        return documentRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found: " + id));
    }

    @Override
    public List<Document> listAll() {
        return documentRepositoryPort.findAll();
    }
}

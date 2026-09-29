package com.example.documentservice.domain.port.in;

import com.example.documentservice.domain.model.Document;

import java.util.List;

public interface ListDocumentsUseCase {

    List<Document> listAll();
}

package com.example.documentservice.domain.port.in;

import com.example.documentservice.domain.model.Document;

public interface UploadDocumentUseCase {

    Document upload(UploadCommand command);

    record UploadCommand(String fileName, String contentType, byte[] content) {
    }
}

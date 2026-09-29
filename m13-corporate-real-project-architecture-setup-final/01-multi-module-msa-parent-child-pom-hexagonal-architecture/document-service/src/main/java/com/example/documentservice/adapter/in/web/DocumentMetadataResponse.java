package com.example.documentservice.adapter.in.web;

import com.example.documentservice.domain.model.Document;

import java.time.Instant;

/**
 * Response DTO that deliberately omits the raw byte[] content - metadata only,
 * used for upload confirmation and listing. Actual bytes are streamed via the
 * dedicated download endpoint.
 */
public record DocumentMetadataResponse(Long id, String fileName, String contentType, long size, Instant uploadedAt) {

    public static DocumentMetadataResponse from(Document document) {
        return new DocumentMetadataResponse(document.getId(), document.getFileName(), document.getContentType(),
                document.getSize(), document.getUploadedAt());
    }
}

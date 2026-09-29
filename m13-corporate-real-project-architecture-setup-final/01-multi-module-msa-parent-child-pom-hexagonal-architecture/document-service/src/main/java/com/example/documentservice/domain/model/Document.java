package com.example.documentservice.domain.model;

import java.time.Instant;

/**
 * Core domain object - framework-free. Content bytes are kept in-memory here and
 * persisted as a BLOB by the outbound adapter.
 */
public class Document {

    private Long id;
    private String fileName;
    private String contentType;
    private long size;
    private byte[] content;
    private Instant uploadedAt;

    public Document() {
    }

    public Document(Long id, String fileName, String contentType, long size, byte[] content, Instant uploadedAt) {
        this.id = id;
        this.fileName = fileName;
        this.contentType = contentType;
        this.size = size;
        this.content = content;
        this.uploadedAt = uploadedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Instant getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Instant uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
}

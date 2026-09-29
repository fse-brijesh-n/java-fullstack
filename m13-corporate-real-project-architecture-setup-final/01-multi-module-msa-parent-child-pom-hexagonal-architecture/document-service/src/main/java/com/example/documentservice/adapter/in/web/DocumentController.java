package com.example.documentservice.adapter.in.web;

import com.example.common.dto.ApiResponse;
import com.example.documentservice.domain.model.Document;
import com.example.documentservice.domain.port.in.DownloadDocumentUseCase;
import com.example.documentservice.domain.port.in.ListDocumentsUseCase;
import com.example.documentservice.domain.port.in.UploadDocumentUseCase;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

@RestController
public class DocumentController {

    private final UploadDocumentUseCase uploadDocumentUseCase;
    private final DownloadDocumentUseCase downloadDocumentUseCase;
    private final ListDocumentsUseCase listDocumentsUseCase;

    public DocumentController(UploadDocumentUseCase uploadDocumentUseCase,
                               DownloadDocumentUseCase downloadDocumentUseCase,
                               ListDocumentsUseCase listDocumentsUseCase) {
        this.uploadDocumentUseCase = uploadDocumentUseCase;
        this.downloadDocumentUseCase = downloadDocumentUseCase;
        this.listDocumentsUseCase = listDocumentsUseCase;
    }

    @PostMapping(value = "/api/documents", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<DocumentMetadataResponse> upload(@RequestParam("file") MultipartFile file) {
        try {
            Document document = uploadDocumentUseCase.upload(new UploadDocumentUseCase.UploadCommand(
                    file.getOriginalFilename(), file.getContentType(), file.getBytes()));
            return ApiResponse.ok("Document uploaded", DocumentMetadataResponse.from(document));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to read uploaded file", e);
        }
    }

    @GetMapping("/api/documents")
    public ApiResponse<List<DocumentMetadataResponse>> list() {
        List<DocumentMetadataResponse> docs = listDocumentsUseCase.listAll().stream()
                .map(DocumentMetadataResponse::from)
                .toList();
        return ApiResponse.ok(docs);
    }

    @GetMapping("/api/documents/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Long id) {
        Document document = downloadDocumentUseCase.download(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getFileName() + "\"")
                .contentType(MediaType.parseMediaType(
                        document.getContentType() != null ? document.getContentType() : "application/octet-stream"))
                .body(document.getContent());
    }
}

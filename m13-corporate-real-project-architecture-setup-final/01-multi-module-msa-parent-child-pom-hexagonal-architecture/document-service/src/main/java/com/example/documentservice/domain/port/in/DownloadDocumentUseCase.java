package com.example.documentservice.domain.port.in;

import com.example.documentservice.domain.model.Document;

public interface DownloadDocumentUseCase {

    Document download(Long id);
}

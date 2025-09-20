package com.fkrf.product_api.model;

import com.fkrf.product_api.enums.DocumentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class Client {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "Binary(16)")
    private UUID id;
    @OneToOne(optional = true)
    @JoinColumn(name = "user_id")
    private User user;
    @NotBlank
    @Column
    private String fullName;
    @NotBlank
    @Column
    private String documentNumber;
    private DocumentType documentType;
    public UUID getId() {
        return id;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }
}

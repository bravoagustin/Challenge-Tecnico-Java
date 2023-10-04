package com.box.ChargeFile.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

//cada campo que se encuentra en esta clase va a ser una columna en nuestra base de datos.
@Entity
public class UploadedFileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "hash_sha_256")
    private String hashSHA256;

    @Column(name = "hash_sha_512")
    private String hashSHA512;

    @Column(name = "last_upload")
    private LocalDateTime lastUpload;

    // Getters y setters

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

    public String getHashSHA256() {
        return hashSHA256;
    }

    public void setHashSHA256(String hashSHA256) {
        this.hashSHA256 = hashSHA256;
    }

    public String getHashSHA512() {
        return hashSHA512;
    }

    public void setHashSHA512(String hashSHA512) {
        this.hashSHA512 = hashSHA512;
    }

    public LocalDateTime getLastUpload() {
        return lastUpload;
    }

    public void setLastUpload(LocalDateTime lastUpload) {
       this.lastUpload = lastUpload;
    }

}


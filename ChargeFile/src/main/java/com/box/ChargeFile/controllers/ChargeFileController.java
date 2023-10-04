package com.box.ChargeFile.controllers;

import com.box.ChargeFile.models.UploadedFileModel;
import com.box.ChargeFile.repositories.UploadFileRepository;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Optional;


@RestController
@RequestMapping("/files")
public class ChargeFileController {
    @Autowired
    private UploadFileRepository fileUploadRepository;
    private UploadedFileModel uploadedFileModel;


    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("algorithm") String algorithm) {
        // el archivo ya existe en la base de datos?
        try {
            String hash = calculateHash(file, algorithm);
            Optional<FileUpload> existingFile = fileUploadRepository.findByHash(hash);

            if (existingFile.isPresent()) {
                // El archivo ya existe, actualizar la fecha de última carga
                FileUpload fileUpload = existingFile.get();
                uploadedFileModel.setLastUpload(LocalDateTime.now());
                UploadFileRepository.save(fileUpload);
                return ResponseEntity.ok(
                        "El archivo ya existe. Se ha actualizado la fecha de carga.");
            } else {
                // El archivo no existe, crear un nuevo registro
                UploadedFileModel newFile = new UploadedFileModel();
                newFile.setFileName(String fileName);
                newFile.setHashSHA256(hash);
                newFile.setHashSHA512(hash);
                newFile.setLastUpload(LocalDateTime.now());
                fileUploadRepository.save(newFile);
                return ResponseEntity.ok("El archivo se ha cargado exitosamente.");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al cargar el archivo.");
        }
    }

    private String calculateHash(MultipartFile file, String algorithm) throws IOException {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] bytes = md.digest(file.getBytes());
            return DatatypeConverter.printHexBinary(bytes).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Algoritmo de hash no válido: " + algorithm);
            throw new RuntimeException(e);
        }
    }
}

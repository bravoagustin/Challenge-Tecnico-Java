package com.box.ChargeFile.services;

import com.box.ChargeFile.models.UploadedFileModel;
import com.box.ChargeFile.repositories.UploadFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Service
public class FileService {
    @Autowired
    private UploadFileRepository fileRepository;

    public ArrayList <UploadedFileModel> getFiles(){
        return (ArrayList<UploadedFileModel>) fileRepository.findAll();
    }

    @Transactional
    public void procesingFile(String fileName, byte[] fileData, String hashAlgorithm) {
        // Calcula el hash del archivo en función del algoritmo proporcionado (SHA-256 o SHA-512).
//           String hash = calcularHash(fileData, hashAlgorithm);
        public UploadedFileModel getFileByHash(String hash) {
            UploadedFileModel file = fileRepository.findByHash(hash);

            // You can perform other operations or validations here if needed.

            return file;
        }

        // Verifica si el archivo ya existe en la base de datos por su hash.
//       UploadFileRepository getFile = UploadFileRepository.findByHash(String hash);
//
//        if (existingFile == null) {
//            // Si el archivo no existe, crea un nuevo registro en la base de datos.
//            UploadedFile newFile = new UploadedFile();
//            newFile.setFileName(fileName);
//            newFile.setHash(hash);
//            fileRepository.save(newFile);
//        } else {
//            // Si el archivo ya existe, actualiza la fecha de última carga.
//            existingFile.setLastUpload(new Date());
//            fileRepository.save(existingFile);
//        }
    }

    private String calcularHash(byte[] fileData, String hashAlgorithm) {
    return "hola";
    }
}




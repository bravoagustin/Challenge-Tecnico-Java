package com.box.ChargeFile.repositories;

import com.box.ChargeFile.models.UploadedFileModel;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UploadFileRepository extends JpaRepository<UploadedFileModel, Long> {
    static void save(FileUpload fileUpload) {
    }

    Optional<FileUpload> findByHash(String hash);

    }


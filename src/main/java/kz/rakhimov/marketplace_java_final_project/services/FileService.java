package kz.rakhimov.marketplace_java_final_project.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    boolean uploadFile(MultipartFile file, Long id);
}

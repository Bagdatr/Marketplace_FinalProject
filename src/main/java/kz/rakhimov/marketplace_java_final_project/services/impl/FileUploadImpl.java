package kz.rakhimov.marketplace_java_final_project.services.impl;

import kz.rakhimov.marketplace_java_final_project.entities.Item;
import kz.rakhimov.marketplace_java_final_project.entities.Photo;
import kz.rakhimov.marketplace_java_final_project.repositories.ItemRepository;
import kz.rakhimov.marketplace_java_final_project.repositories.PhotoRepository;
import kz.rakhimov.marketplace_java_final_project.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadImpl implements FileService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public boolean uploadFile(MultipartFile file, Long id) {
        try {
            Item item = itemRepository.findAllById(id);
            String fileName = item.getName() + item.getId();
            String finalFileName = "";
            for (int i = 0; i < fileName.length(); i++) {
                if(fileName.charAt(i) != '/' && fileName.charAt(i) != '.' && fileName.charAt(i) != ','){
                    finalFileName = finalFileName + fileName.charAt(i);
                }
            }
            Photo photo = Photo.builder()
                    .name(finalFileName)
                    .build();
            byte bytes[] = file.getBytes();
            Path path = Paths.get("build/resources/main/static/" + finalFileName + ".jpg");
            Files.write(path, bytes);
            item.getPhotos().add(photo);
            photoRepository.save(photo);
            itemRepository.save(item);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

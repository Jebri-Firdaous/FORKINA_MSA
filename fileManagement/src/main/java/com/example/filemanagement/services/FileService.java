package com.example.filemanagement.services;

import com.example.filemanagement.entities.FileEntity;
import com.example.filemanagement.repositories.FileRepository;
import com.example.filemanagement.utils.FileContentStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    private FileContentStore contentStore;

    public FileEntity uploadFile(MultipartFile file) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(file.getOriginalFilename());
        fileEntity.setFileType(file.getContentType());
        fileEntity.setSize(file.getSize());
        fileEntity.setCreatedAt(LocalDateTime.now());

        fileEntity = fileRepository.save(fileEntity);

        contentStore.setContent(fileEntity, file.getInputStream());

        return fileEntity;
    }
    public List<FileEntity> getAllfiles() {
        return fileRepository.findAll();
    }

    public InputStream downloadFile(Long id) throws FileNotFoundException {
        FileEntity fileEntity = fileRepository.findById(id)
                .orElseThrow(() -> new FileNotFoundException("File not found"));
        return contentStore.getContent(fileEntity);
    }

    public void deleteFile(Long id) throws FileNotFoundException {
        FileEntity fileEntity = fileRepository.findById(id)
                .orElseThrow(() -> new FileNotFoundException("File not found"));
        contentStore.unsetContent(fileEntity);
        fileRepository.delete(fileEntity);
    }
}

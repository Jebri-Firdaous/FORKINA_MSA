package com.example.filemanagement.services;

import com.example.filemanagement.entities.FileEntity;
import com.example.filemanagement.repositories.FileRepository;
import com.example.filemanagement.utils.FileContentStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    private FileContentStore contentStore;

    @Value("${file.upload-dir}")
    private String uploadDir;

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

    public String saveFile(MultipartFile file) throws IOException {
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        String fileName = UUID.randomUUID() + "_" + originalFilename;

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);

        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }

    public File getFile(String fileName) {
        return new File(uploadDir + "/" + fileName);
    }
}

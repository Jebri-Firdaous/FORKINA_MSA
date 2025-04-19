package com.example.filemanagement.controllers;

import com.example.filemanagement.entities.FileEntity;
import com.example.filemanagement.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;
    @GetMapping
    public String healthCheck() {
        return "File Management Microservice is running!";
    }

    @PostMapping("/add")
    public ResponseEntity<FileEntity> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        FileEntity uploadedFile = fileService.uploadFile(file);
        return ResponseEntity.ok(uploadedFile);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FileEntity>> getAllFiles() {
        List<FileEntity> milestones = fileService.getAllfiles();
        return ResponseEntity.ok(milestones);
    }


    @GetMapping("/{id}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable Long id) throws FileNotFoundException {
        InputStream stream = fileService.downloadFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(stream));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable Long id) throws FileNotFoundException {
        fileService.deleteFile(id);
        return ResponseEntity.noContent().build();
    }
}

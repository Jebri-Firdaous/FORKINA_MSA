package com.example.filemanagement.utils;

import com.example.filemanagement.entities.FileEntity;
import org.springframework.content.commons.repository.ContentStore;
import org.springframework.content.rest.StoreRestResource;

import java.io.InputStream;

@StoreRestResource(path = "./upload")
public interface FileContentStore extends ContentStore<FileEntity, String> {
}
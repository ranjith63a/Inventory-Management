package com.org.invmgm.service;

import com.org.invmgm.dto.CustomFileResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String upload(MultipartFile file) throws IOException;
    CustomFileResource download(Long id) throws IOException;
}

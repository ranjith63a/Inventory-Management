package com.org.invmgm.service.impl;

import com.org.invmgm.config.FileStorageConfig;
import com.org.invmgm.dto.CustomFileResource;
import com.org.invmgm.exception.InvalidFileTypeException;
import com.org.invmgm.model.FileContent;
import com.org.invmgm.repository.FileContentRepository;
import com.org.invmgm.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {

    private final FileStorageConfig fileStorageConfig;
    private final FileContentRepository fileContentRepository;


    private static final List<String> ALLOWED_TYPES =
            List.of(
                    "image/png",
                    "image/jpeg",
                    "application/pdf"
            );


    @Override
    public String upload(MultipartFile file) throws IOException {

        if(file.isEmpty()){
            throw new RuntimeException("File is empty");
        }

        if(!ALLOWED_TYPES.contains(file.getContentType())){
            throw new InvalidFileTypeException("Invalid file type");
        }

        String storedName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        Path uploadPath = Paths.get(fileStorageConfig.getUploadDir());

        // Create folder if not exists
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Save file
        Path path = uploadPath.resolve(storedName);

        Files.copy(file.getInputStream(),
                path,
                StandardCopyOption.REPLACE_EXISTING);

        FileContent content = new FileContent(storedName, file.getOriginalFilename(), file.getContentType(),
                file.getSize(), path.toString());

        content = fileContentRepository.save(content);
        return "Uploaded successfully" + " [" + content.getFileName() + "]";
    }

    @Override
    public CustomFileResource download(Long id) throws IOException {

        FileContent content = fileContentRepository.findById(id)
                .orElseThrow(() -> new FileNotFoundException("File not found"));

        log.info("File name = =  " + content.getFileName());
        log.info("File getFilePath = =  " + content.getFilePath());


        Path path = Paths.get(content.getFilePath());

        Resource resource = new UrlResource(path.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            throw new FileNotFoundException("File not found: " + content.getFileName());
        }

        String contentType = Files.probeContentType(path);

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return new CustomFileResource(resource, contentType, content.getFileName());
    }

}

package com.org.invmgm.model;

import com.org.invmgm.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "file_content")
@Getter
@Setter
@NoArgsConstructor
public class FileContent extends BaseEntity {

    @Id
    @GeneratedValue

    private Long id;
    private String fileName;

    private String storedName;

    private String contentType;

    private Long size;

    private String filePath;

    public FileContent(String storedName, String originalFilename, String contentType, long size, String filePath) {
        this.storedName = storedName;
        this.fileName = originalFilename;
        this.contentType = contentType;
        this.size = size;
        this.filePath = filePath;
    }
}

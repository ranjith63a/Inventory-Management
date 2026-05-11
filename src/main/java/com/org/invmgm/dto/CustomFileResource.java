package com.org.invmgm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.io.Resource;

@Getter
@AllArgsConstructor
public class CustomFileResource {

    private Resource resource;
    private String contentType;
    private String fileName;
}
package com.szalai.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    @GetMapping("/health")
    public HealthDto health() {
        log.info("Client health check started");
        return new HealthDto("OK");
    }

    @GetMapping("/health/oops")
    public HealthDto healthOops() {
        log.info("Client health check started");
        throw new IllegalStateException("Oops");
    }

    @PostMapping(path = "/file", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public FileStatusDto importFile(@RequestPart MultipartFile file) {
        try {
            Path path = Paths.get(String.format("client/src/main/resources/files/%s", file.getOriginalFilename()));
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            log.info("File imported successfully");
            return new FileStatusDto(file.getOriginalFilename(), file.getSize());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

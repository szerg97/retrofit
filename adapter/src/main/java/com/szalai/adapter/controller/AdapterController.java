package com.szalai.adapter.controller;

import com.szalai.adapter.service.AdapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/adapter")
@RequiredArgsConstructor
public class AdapterController {

    private final AdapterService adapterService;

    @GetMapping("/health")
    public HealthDto health() {
        return adapterService.getHealth().body();
    }

    @PostMapping(path = "/file")
    public FileStatusDto file(@RequestBody FileParamsDto paramsDto) {
        return adapterService.createFile(paramsDto).body();
    }
}

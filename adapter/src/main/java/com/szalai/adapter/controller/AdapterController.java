package com.szalai.adapter.controller;

import com.szalai.adapter.exception.CustomException;
import com.szalai.adapter.service.AdapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import retrofit2.Response;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/adapter")
@RequiredArgsConstructor
public class AdapterController {

    private final AdapterService adapterService;

    @GetMapping("/health")
    public HealthDto health() {
        Response<HealthDto> healthDtoResponse = Optional.of(adapterService.getHealth())
                .filter(h -> h.body() != null)
                .orElseThrow(CustomException::connectionToClientFailure);
        return healthDtoResponse.body();
    }

    @GetMapping("/health/oops")
    public HealthDto healthOops() {
        Response<HealthDto> healthDtoResponse = Optional.of(adapterService.getHealthOops())
                .filter(h -> h.body() != null)
                .orElseThrow(CustomException::connectionToClientFailure);
        return healthDtoResponse.body();
    }

    @PostMapping(path = "/file")
    public FileStatusDto file(@RequestBody FileParamsDto paramsDto) {
        return adapterService.createFile(paramsDto).body();
    }
}

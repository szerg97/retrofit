package com.szalai.adapter.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileStatusDto {
    private String fileName;
    private long fileSize;
}

package com.szalai.adapter.validator;

import com.szalai.adapter.controller.FileParamsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
public class AdapterControllerValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        if (clazz.equals(FileParamsDto.class)) {
            return true;
        }
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof FileParamsDto fileParamsDto) {
            validateFileParamsDto(fileParamsDto);
        }
    }

    private void validateFileParamsDto(FileParamsDto fileParamsDto) {
        log.info("validating FileParamsDto...");
    }
}

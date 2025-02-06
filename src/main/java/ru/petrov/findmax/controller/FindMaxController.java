package ru.petrov.findmax.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.petrov.findmax.model.FileNotValid;
import ru.petrov.findmax.model.RequestDto;
import ru.petrov.findmax.service.FindMaxService;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FindMaxController {
    private final FindMaxService findMaxService;

    @Operation(summary = "Получить n-ое максимальное число",
            description = "Указывается путь к файлу xlsx.")
    @PostMapping
    public ResponseEntity<Long> test (@RequestBody @Valid RequestDto requestDto) {
        Long nMax = findMaxService.findNMax(requestDto.getPath(), requestDto.getN());
        return new ResponseEntity<>(nMax, HttpStatus.OK);
    }

    @ExceptionHandler(FileNotValid.class)
    public ResponseEntity<String> handleCustomException(FileNotValid ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}


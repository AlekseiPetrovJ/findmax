package ru.petrov.findmax.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
            description = "path - Указывается путь к файлу xlsx. В windows в пути следует использовать прямой слэш / " +
                    "Например: C:/file/small.xlsx" +
                    "\n\n Либо можно разместить файл в каталоге программы и указать имя файла. " +
                    "\n\n Для примера в каталоге уже размещены " +
                    "файлы с тестовыми данными: small.xlsx, medium.xlsx, big.xlsx, big_sort.xlsx" +
                    "\n\n n - требуемая позиция максимального числа. Традиционно нумерация начинается с нуля")
    @PostMapping
    public ResponseEntity<Long> test (@RequestBody @Valid RequestDto requestDto) {
        Long nMax = findMaxService.findNMax(requestDto.getPath(), requestDto.getN());
        return new ResponseEntity<>(nMax, HttpStatus.OK);
    }

    @ExceptionHandler({FileNotValid.class, MethodArgumentNotValidException.class})
    public ResponseEntity<String> handleCustomException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}


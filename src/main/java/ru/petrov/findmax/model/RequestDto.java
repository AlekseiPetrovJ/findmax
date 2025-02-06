package ru.petrov.findmax.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestDto {
    @Schema(description = "Путь к файлу xlsx с данными", example = "C:\\Users\\user\\test\\test.xlsx")
    @NotBlank
    String path;

    @Schema(description = "Позиция максимального числа", example = "2")
    @Min(value = 1, message = "Номер позиции должен быть больше единицы")
    @Max(value = 1048576, message = "файл формата xlsx не может содержать более  1048576 строк") //
    Integer n;
}

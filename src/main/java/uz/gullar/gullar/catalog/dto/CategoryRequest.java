package uz.gullar.gullar.catalog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequest(
        @NotBlank(message = "Kategoriya nomi bo'sh bo'lmasligi kerak")
        @Size(max = 100, message = "Kategoriya nomi 100 belgidan oshmasligi kerak")
        String name,

        String description
) {
}
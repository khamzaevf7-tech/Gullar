package uz.gullar.gullar.catalog.dto;

public record CategoryResponse(
        Long id,
        String name,
        String description,
        boolean active
) {
}
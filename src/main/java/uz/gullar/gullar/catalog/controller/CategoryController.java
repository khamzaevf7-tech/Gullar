package uz.gullar.gullar.catalog.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.gullar.gullar.catalog.dto.CategoryRequest;
import uz.gullar.gullar.catalog.dto.CategoryResponse;
import uz.gullar.gullar.catalog.entity.Category;
import uz.gullar.gullar.catalog.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(
            @Valid @RequestBody CategoryRequest request
    ) {
        Category category = categoryService.create(
                request.name(),
                request.description()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(toResponse(category));
    }

    @GetMapping
    public List<CategoryResponse> findAll() {
        return categoryService.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public CategoryResponse findById(@PathVariable Long id) {
        return toResponse(categoryService.findById(id));
    }

    @PutMapping("/{id}")
    public CategoryResponse update(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequest request
    ) {
        Category category = categoryService.update(
                id,
                request.name(),
                request.description()
        );

        return toResponse(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        categoryService.setActive(id, false);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/active")
    public CategoryResponse setActive(
            @PathVariable Long id,
            @RequestParam boolean active
    ) {
        return toResponse(categoryService.setActive(id, active));
    }

    private CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.isActive()
        );
    }
}
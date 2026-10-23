package uz.gullar.gullar.catalog.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.gullar.gullar.catalog.entity.Category;
import uz.gullar.gullar.catalog.repository.CategoryRepository;
import uz.gullar.gullar.common.exception.BusinessException;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public Category create(String name, String description) {
        if (categoryRepository.existsByName(name)) {
            throw new BusinessException(
                    HttpStatus.CONFLICT,
                    "CATEGORY_NAME_ALREADY_EXISTS",
                    "Bu kategoriya nomi allaqachon mavjud"
            );
        }

        return categoryRepository.save(new Category(name, description));
    }

    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        HttpStatus.NOT_FOUND,
                        "CATEGORY_NOT_FOUND",
                        "Kategoriya topilmadi"
                ));
    }

    @Transactional
    public Category update(Long id, String name, String description) {
        Category category = findById(id);

        if (categoryRepository.existsByNameAndIdNot(name, id)) {
            throw new BusinessException(
                    HttpStatus.CONFLICT,
                    "CATEGORY_NAME_ALREADY_EXISTS",
                    "Bu kategoriya nomi allaqachon mavjud"
            );
        }

        category.updateDetails(name, description);
        return categoryRepository.save(category);
    }

    @Transactional
    public Category setActive(Long id, boolean active) {
        Category category = findById(id);
        category.setActive(active);
        return categoryRepository.save(category);
    }
}
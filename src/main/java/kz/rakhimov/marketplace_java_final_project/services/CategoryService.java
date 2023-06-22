package kz.rakhimov.marketplace_java_final_project.services;

import kz.rakhimov.marketplace_java_final_project.entities.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();
    CategoryDto addCategory(CategoryDto categoryDto);
    void deleteCategory(Long id);
}
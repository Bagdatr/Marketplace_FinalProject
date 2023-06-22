package kz.rakhimov.marketplace_java_final_project.services.impl;

import kz.rakhimov.marketplace_java_final_project.entities.Category;
import kz.rakhimov.marketplace_java_final_project.entities.CategoryDto;
import kz.rakhimov.marketplace_java_final_project.mapper.CategoryMapper;
import kz.rakhimov.marketplace_java_final_project.repositories.CategoryRepository;
import kz.rakhimov.marketplace_java_final_project.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.mapToDtoList(categories);
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        return categoryMapper.mapToDto(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

}
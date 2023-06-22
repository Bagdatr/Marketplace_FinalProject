package kz.rakhimov.marketplace_java_final_project.controllers;

import kz.rakhimov.marketplace_java_final_project.entities.CategoryDto;
import kz.rakhimov.marketplace_java_final_project.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping
    public CategoryDto addCategoryPost(@RequestBody CategoryDto categoryDto){
        return categoryService.addCategory(categoryDto);
    }

    @DeleteMapping(value="/{id}")
    void deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
    }
}

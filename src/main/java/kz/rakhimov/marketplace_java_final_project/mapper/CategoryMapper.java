package kz.rakhimov.marketplace_java_final_project.mapper;

import kz.rakhimov.marketplace_java_final_project.entities.Category;
import kz.rakhimov.marketplace_java_final_project.entities.CategoryDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto mapToDto(Category category);
    List<CategoryDto> mapToDtoList(List<Category> categories);
    Category mapToEntity (CategoryDto categoryDto);
}
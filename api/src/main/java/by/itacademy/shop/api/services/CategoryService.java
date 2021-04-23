package by.itacademy.shop.api.services;

import by.itacademy.shop.api.dto.CategoryDto;
import by.itacademy.shop.api.dto.UserDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto user);
    CategoryDto find(long id);
    void update(CategoryDto user);
    void delete(long id);

    List<CategoryDto> getAllCategories();
}

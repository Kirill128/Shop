package by.itacademy.shop.api.services;

import by.itacademy.shop.api.dto.GuestCategoryDto;
import by.itacademy.shop.api.dto.admin.CategoryDto;
import by.itacademy.shop.locale.Lang;

import java.util.List;

public interface CategoryService {
    GuestCategoryDto find(long id,Lang lang);
    List<GuestCategoryDto> getAllCategories(Lang lang);

    //Admin
    CategoryDto createCategory(CategoryDto user);
    CategoryDto findFullInfo(long id);
    void update(CategoryDto user);
    void delete(long id);
    List<CategoryDto> getAllCategoriesFullInfo();

}

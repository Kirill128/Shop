package by.itacademy.shop.api.services;

import by.itacademy.shop.api.dto.forall.GuestParentCategoryDto;
import by.itacademy.shop.api.dto.admin.CategoryDto;
import by.itacademy.shop.api.dto.admin.ParentCategoryDto;
import by.itacademy.shop.locale.Lang;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface CategoryService {
    GuestParentCategoryDto find(long id, Lang lang);
    List<GuestParentCategoryDto> getParentCategories(Lang lang);

    //Admin
    CategoryDto createCategory(CategoryDto user) throws JsonProcessingException;
    CategoryDto findFullInfo(long id) throws JsonProcessingException;
    void update(CategoryDto user) throws JsonProcessingException;
    void delete(long id);
    List<CategoryDto> getAllCategoriesFullInfo() throws JsonProcessingException;
    List<ParentCategoryDto> getParentCategoriesFullInfo() throws JsonProcessingException;
}

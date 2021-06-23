package by.itacademy.shop.api.services;

import by.itacademy.shop.api.dto.admin.AdminCategoryDto;
import by.itacademy.shop.api.dto.admin.AdminParentCategoryDto;
import by.itacademy.shop.api.dto.forall.GuestParentCategoryDto;
import by.senla.daomicroservice.microservices.constants.Lang;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface CategoryService {
    GuestParentCategoryDto find(long id, Lang lang);
    List<GuestParentCategoryDto> getParentCategories(Lang lang);

    //Admin
    AdminCategoryDto createCategory(AdminCategoryDto user) throws JsonProcessingException;
    AdminCategoryDto findFullInfo(long id) throws JsonProcessingException;
    void update(AdminCategoryDto user) throws JsonProcessingException;
    void delete(long id);
    List<AdminCategoryDto> getAllCategoriesFullInfo() throws JsonProcessingException;
    List<AdminParentCategoryDto> getParentCategoriesFullInfo() throws JsonProcessingException;
    List<AdminCategoryDto> getSubCategoriesFullInfo() throws JsonProcessingException;
}

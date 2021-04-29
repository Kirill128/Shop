package by.itacademy.shop.api.mappers;

import by.itacademy.shop.api.dto.GuestCategoryDto;
import by.itacademy.shop.api.dto.GuestSubCategoryDto;
import by.itacademy.shop.api.dto.admin.CategoryDto;
import by.itacademy.shop.entities.Category;
import by.itacademy.shop.locale.Lang;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CategoryMapper {
    public GuestSubCategoryDto mapCategoryToGuestSubCategoryDto(Category category, Lang lang){
        return GuestSubCategoryDto.builder().
                id(category.getId()).
                title(category.getTitle().get(lang.value)).
                build();
    }
    public List<GuestSubCategoryDto> mapCategoriesToSubCategoryDtos(List<Category> categories,Lang lang){
        return categories.stream().map(e-> mapCategoryToGuestSubCategoryDto(e,lang)).collect(Collectors.toList());
    }
    public GuestCategoryDto mapCategoryToGuestCategoryDto(Category category, Lang lang) {
        return GuestCategoryDto.builder().
                id(category.getId()).
                title(category.getTitle().get(lang.value)).
                subcategories(CategoryMapper.mapCategoriesToSubCategoryDtos(category.getSubCategories(),lang)).
                build();
    }

    public List<GuestCategoryDto> mapCategoriesToGuestCategoryDtos(List<Category> categoryList, Lang lang) {
        return categoryList.stream().map((e) -> CategoryMapper.mapCategoryToGuestCategoryDto(e, lang)).collect(Collectors.toList());
    }

    //--------------------CategoryDto
    public CategoryDto mapCategoryToCategoryDto(Category category) {
        return CategoryDto.builder().
                id(category.getId()).
                title(category.getTitle()).
                parentCategory(category.getParentCategory()).
                build();
    }

    public List<CategoryDto> mapCategoriesToCategoryDtos(List<Category> categoryList) {
        return categoryList.stream().map(CategoryMapper::mapCategoryToCategoryDto).collect(Collectors.toList());
    }


    public Category mapCategoryDtoToCategory(CategoryDto category) {
        return Category.builder().
                id(category.getId()).
                title(category.getTitle()).
                parentCategory(category.getParentCategory()).
                build();
    }

    public List<Category> mapCategoryDtosToCategories(List<CategoryDto> categoryList) {
        return categoryList.stream().map(CategoryMapper::mapCategoryDtoToCategory).collect(Collectors.toList());
    }


    //--------------------
}

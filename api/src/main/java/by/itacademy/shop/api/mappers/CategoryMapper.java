package by.itacademy.shop.api.mappers;

import by.itacademy.shop.api.dto.GuestParentCategoryDto;
import by.itacademy.shop.api.dto.GuestSubCategoryDto;
import by.itacademy.shop.api.dto.admin.category.CategoryDto;
import by.itacademy.shop.api.dto.admin.category.ParentCategoryDto;
import by.itacademy.shop.api.dto.admin.category.SubCategoryDto;
import by.itacademy.shop.entities.Category;
import by.itacademy.shop.locale.Lang;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CategoryMapper {
    //-------------------------------GuestSubCategoryDto------------------------------------------for users

    public GuestSubCategoryDto mapCategoryToGuestSubCategoryDto(Category category, Lang lang){
        return GuestSubCategoryDto.builder().
                id(category.getId()).
                title(category.getTitle().get(lang.value)).
                build();
    }
    public List<GuestSubCategoryDto> mapCategoriesToSubCategoryDtos(List<Category> categories,Lang lang){
        return categories.stream().map(e-> mapCategoryToGuestSubCategoryDto(e,lang)).collect(Collectors.toList());
    }
    //-------------------------------GuestParentCategoryDto------------------------------------------for users

    public GuestParentCategoryDto mapCategoryToGuestCategoryDto(Category category, Lang lang) {
        return GuestParentCategoryDto.builder().
                id(category.getId()).
                title(category.getTitle().get(lang.value)).
                subcategories(CategoryMapper.mapCategoriesToSubCategoryDtos(category.getSubCategories(),lang)).
                build();
    }

    public List<GuestParentCategoryDto> mapCategoriesToGuestCategoryDtos(List<Category> categoryList, Lang lang) {
        return categoryList.stream().map((e) -> CategoryMapper.mapCategoryToGuestCategoryDto(e, lang)).collect(Collectors.toList());
    }

    //-------------------------------CategoryDto------------------------------------------for admin
    public CategoryDto mapCategoryToCategoryDto(Category category) throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        return CategoryDto.builder().
                id(category.getId()).
                title(mapper.writeValueAsString(category.getTitle())).
                parentCategory(category.getParentCategory()).
                subCategories(CategoryMapper.mapCategoriesToSubcategoryDtos(category.getSubCategories())).
                build();
    }

    public Category mapCategoryDtoToCategory(CategoryDto category) throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        return Category.builder().
                id(category.getId()).
                title(mapper.readValue(category.getTitle(),new TypeReference<HashMap<String, String>>(){})).
                parentCategory(category.getParentCategory()).
                subCategories(category.getSubCategories()).
                build();
    }

    public List<CategoryDto> mapCategoriesToCategoryDtos(List<Category> categoryList) throws JsonProcessingException {
        List<CategoryDto> result = new ArrayList<>(categoryList.size());
        for (Category category : categoryList) {
            result.add(CategoryMapper.mapCategoryToCategoryDto(category));
        }
        return result;
    }


    public List<Category> mapCategoryDtosToCategories(List<CategoryDto> categoryList) throws JsonProcessingException {
        List<Category> result = new ArrayList<>(categoryList.size());
        for (CategoryDto category : categoryList) {
            result.add(CategoryMapper.mapCategoryDtoToCategory(category));
        }
        return result;
    }
    //----------------------------ParentCategoryDto----------------------------------------------for admin
    public ParentCategoryDto mapCategoryToParentCategoryDto(Category category) throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        return ParentCategoryDto.builder().
                id(category.getId()).
                title(mapper.writeValueAsString(category.getTitle())).
                build();
    }
    public List<ParentCategoryDto> mapCategoriesToParentCategoryDtos(List<Category> categories) throws JsonProcessingException {
        List<ParentCategoryDto> result=new ArrayList<>(categories.size());
        for(Category cat: categories){
            result.add(CategoryMapper.mapCategoryToParentCategoryDto(cat));
        }
        return result;
    }
    //----------------------------SubCategoryDto----------------------------------------------for admin

    public SubCategoryDto mapCategoryToSubcategoryDto(Category source) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        return SubCategoryDto.builder().
                id(source.getId()).
                title(objectMapper.writeValueAsString(source.getTitle())).
                build();
    }
    public List<SubCategoryDto> mapCategoriesToSubcategoryDtos(List<Category> source) throws JsonProcessingException {
        List<SubCategoryDto> result = new ArrayList<>(source.size());
        for (Category category : source) {
            result.add(CategoryMapper.mapCategoryToSubcategoryDto(category));
        }
        return result;
    }

    //---------------------------------------------------------------------------------
}

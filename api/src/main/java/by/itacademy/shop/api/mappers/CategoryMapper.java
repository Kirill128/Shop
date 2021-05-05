package by.itacademy.shop.api.mappers;

import by.itacademy.shop.api.dto.GuestParentCategoryDto;
import by.itacademy.shop.api.dto.GuestSubCategoryDto;
import by.itacademy.shop.api.dto.admin.category.CategoryDto;
import by.itacademy.shop.api.dto.admin.category.CategoryDtoFromFront;
import by.itacademy.shop.api.dto.admin.category.ParentCategoryDto;
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


    //----------------------------ParentCategoryDto----------------------------------------------for admin
    public ParentCategoryDto mapCategoryToParentCategoryDto(Category category) throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        return ParentCategoryDto.builder().
                id(category.getId()).
                title( (category.getTitle()!=null)? mapper.writeValueAsString(category.getTitle()) : null).
                subcategories( (category.getSubCategories()!=null)? CategoryMapper.mapCategoriesToCategoryDtos(category.getSubCategories()) : null).
                build();
    }
    public Category mapParentCategoryDtoToCategory(ParentCategoryDto category) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        return Category.builder().
                id(category.getId()).
                title( (category.getTitle()!=null) ? objectMapper.readValue(category.getTitle(),new TypeReference<HashMap<String, String>>(){}) : null).
                build();
    }

    public List<ParentCategoryDto> mapCategoriesToParentCategoryDtos(List<Category> categories) throws JsonProcessingException {
        List<ParentCategoryDto> result=new ArrayList<>(categories.size());
        for(Category cat: categories){
            result.add(CategoryMapper.mapCategoryToParentCategoryDto(cat));
        }
        return result;
    }
    public List<Category> mapParentCategoryDtosToCategories(List<ParentCategoryDto> categories) throws JsonProcessingException {
        List<Category> result=new ArrayList<>(categories.size());
        for(ParentCategoryDto cat: categories){
            result.add(CategoryMapper.mapParentCategoryDtoToCategory(cat));
        }
        return result;
    }

    //----------------------------CategoryDto----------------------------------------------for admin

    public CategoryDto mapCategoryToCategoryDto(Category source) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        return CategoryDto.builder().
                id(source.getId()).
                title((source.getTitle()!=null) ? objectMapper.writeValueAsString(source.getTitle()): null).
                build();
    }
    public Category mapCategoryDtoToCategory(CategoryDto source) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        return Category.builder().
                id(source.getId()).
                title( (source.getTitle()!=null) ? objectMapper.readValue(source.getTitle(),new TypeReference<HashMap<String, String>>(){}) : null).
                parentCategory(  (source.getParentCategoryDto()!=null)?
                        CategoryMapper.mapParentCategoryDtoToCategory(source.getParentCategoryDto()) : null).
                build();
    }
    public List<CategoryDto> mapCategoriesToCategoryDtos(List<Category> source) throws JsonProcessingException {
        List<CategoryDto> result = new ArrayList<>(source.size());
        for (Category category : source) {
            result.add(CategoryMapper.mapCategoryToCategoryDto(category));
        }
        return result;
    }
    public List<Category> mapCategoryDtosToCategories(List<CategoryDto> source) throws JsonProcessingException {
        List<Category> result = new ArrayList<>(source.size());
        for (CategoryDto category : source) {
            result.add(CategoryMapper.mapCategoryDtoToCategory(category));
        }
        return result;
    }

    //-------------------------------CategoryDtoFromFront--------------------------------------------------
    public CategoryDto mapCategoryDtoFromFrontToCategoryDto(CategoryDtoFromFront source){
        return CategoryDto.builder().
                id(source.getId()).
                title(source.getTitle()).
                parentCategoryDto(ParentCategoryDto.builder().
                        id(source.getParentCategoryId()).
                        build()).
                build();
    }
}

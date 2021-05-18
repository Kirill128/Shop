package by.itacademy.shop.api.mappers;

import by.itacademy.shop.api.dto.forall.GuestParentCategoryDto;
import by.itacademy.shop.api.dto.forall.GuestSubCategoryDto;
import by.itacademy.shop.api.dto.admin.CategoryDto;
import by.itacademy.shop.api.dto.admin.ParentCategoryDto;
import by.itacademy.shop.entities.Category;
import by.itacademy.shop.utilenum.Lang;
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
        if(category==null)return null;
        return GuestSubCategoryDto.builder().
                id(category.getId()).
                title(category.getTitle().get(lang.value)).
                build();
    }
    public List<GuestSubCategoryDto> mapCategoriesToSubCategoryDtos(List<Category> categories,Lang lang){
        if(categories==null)return new ArrayList<>();
        return categories.stream().map(e-> mapCategoryToGuestSubCategoryDto(e,lang)).collect(Collectors.toList());
    }
    //-------------------------------GuestParentCategoryDto------------------------------------------for users

    public GuestParentCategoryDto mapCategoryToGuestCategoryDto(Category category, Lang lang) {
        if(category==null)return null;
        return GuestParentCategoryDto.builder().
                id(category.getId()).
                title(category.getTitle().get(lang.value)).
                subcategories(CategoryMapper.mapCategoriesToSubCategoryDtos(category.getSubCategories(),lang)).
                build();
    }

    public List<GuestParentCategoryDto> mapCategoriesToGuestCategoryDtos(List<Category> categoryList, Lang lang) {
        if(categoryList==null)return new ArrayList<>();
        return categoryList.stream().map((e) -> CategoryMapper.mapCategoryToGuestCategoryDto(e, lang)).collect(Collectors.toList());
    }


    //----------------------------ParentCategoryDto----------------------------------------------for admin
    public ParentCategoryDto mapCategoryToParentCategoryDto(Category category) throws JsonProcessingException {
        if(category==null)return null;
        ObjectMapper mapper=new ObjectMapper();
        return ParentCategoryDto.builder().
                id(category.getId()).
                title( (category.getTitle()!=null)? mapper.writeValueAsString(category.getTitle()) : null).
                subcategories( CategoryMapper.mapCategoriesToCategoryDtos(category.getSubCategories()) ).
                build();
    }
    public Category mapParentCategoryDtoToCategory(ParentCategoryDto category) throws JsonProcessingException {
        if(category==null)return null;
        ObjectMapper objectMapper=new ObjectMapper();
        return Category.builder().
                id(category.getId()).
                title( (category.getTitle()!=null) ? objectMapper.readValue(category.getTitle(),new TypeReference<HashMap<String, String>>(){}) : null).
                build();
    }

    public List<ParentCategoryDto> mapCategoriesToParentCategoryDtos(List<Category> categories) throws JsonProcessingException {
        if(categories==null)return new ArrayList<>();
        List<ParentCategoryDto> result=new ArrayList<>(categories.size());
        for(Category cat: categories){
            result.add(CategoryMapper.mapCategoryToParentCategoryDto(cat));
        }
        return result;
    }
    public List<Category> mapParentCategoryDtosToCategories(List<ParentCategoryDto> categories) throws JsonProcessingException {
        if(categories==null)return new ArrayList<>();
        List<Category> result=new ArrayList<>(categories.size());
        for(ParentCategoryDto cat: categories){
            result.add(CategoryMapper.mapParentCategoryDtoToCategory(cat));
        }
        return result;
    }

    //----------------------------CategoryDto----------------------------------------------for admin

    public CategoryDto mapCategoryToCategoryDto(Category source) throws JsonProcessingException {
        if(source==null)return null;
        ObjectMapper objectMapper=new ObjectMapper();
        return CategoryDto.builder().
                id(source.getId()).
                title((source.getTitle()!=null) ? objectMapper.writeValueAsString(source.getTitle()): null).
                parentCategoryId((source.getParentCategory()!=null)?source.getParentCategory().getId() : null).
                build();
    }
    public Category mapCategoryDtoToCategory(CategoryDto source) throws JsonProcessingException {
        if(source==null)return null;
        ObjectMapper objectMapper=new ObjectMapper();
        Category parentCategory=null;
        if(source.getParentCategoryDto()==null){
            if(source.getParentCategoryId()!=null){
                parentCategory=Category.builder().id(source.getParentCategoryId()).build();
            }
        }else
            parentCategory=CategoryMapper.mapParentCategoryDtoToCategory(source.getParentCategoryDto());
        return Category.builder().
                id(source.getId()).
                title( (source.getTitle()!=null) ? objectMapper.readValue(source.getTitle(),new TypeReference<HashMap<String, String>>(){}) : null).
                parentCategory(parentCategory).
                build();
    }
    public List<CategoryDto> mapCategoriesToCategoryDtos(List<Category> source) throws JsonProcessingException {
        if(source==null)return new ArrayList<>();
        List<CategoryDto> result = new ArrayList<>(source.size());
        for (Category category : source) {
            result.add(CategoryMapper.mapCategoryToCategoryDto(category));
        }
        return result;
    }
    public List<Category> mapCategoryDtosToCategories(List<CategoryDto> source) throws JsonProcessingException {
        if(source==null)return new ArrayList<>();
        List<Category> result = new ArrayList<>(source.size());
        for (CategoryDto category : source) {
            result.add(CategoryMapper.mapCategoryDtoToCategory(category));
        }
        return result;
    }


}

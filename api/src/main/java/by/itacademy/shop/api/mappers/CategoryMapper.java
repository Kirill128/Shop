package by.itacademy.shop.api.mappers;

import by.itacademy.shop.api.dto.CategoryDto;
import by.itacademy.shop.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE= Mappers.getMapper(CategoryMapper.class);
    CategoryDto mapUserDto(Category source);
    Category mapUser(CategoryDto source);
    List<CategoryDto> mapUserDtos(List<Category> source);
    List<Category> mapUsers(List<CategoryDto> source);
}

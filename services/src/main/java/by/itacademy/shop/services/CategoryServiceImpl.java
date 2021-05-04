package by.itacademy.shop.services;

import by.itacademy.shop.api.dao.CategoryDao;
import by.itacademy.shop.api.dto.GuestParentCategoryDto;
import by.itacademy.shop.api.dto.admin.category.CategoryDto;
import by.itacademy.shop.api.dto.admin.category.ParentCategoryDto;
import by.itacademy.shop.api.mappers.CategoryMapper;
import by.itacademy.shop.api.services.CategoryService;
import by.itacademy.shop.entities.Category;
import by.itacademy.shop.locale.Lang;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    //-------------User
    @Override
    public GuestParentCategoryDto find(long id, Lang lang) {
        return CategoryMapper.mapCategoryToGuestCategoryDto(this.categoryDao.find(id),lang);
    }

    @Override
    public List<GuestParentCategoryDto> getParentCategories(Lang lang) {
        return CategoryMapper.mapCategoriesToGuestCategoryDtos(this.categoryDao.getParentCategories(),lang);
    }

    //--------------Admin
    @Override
    public CategoryDto createCategory(CategoryDto user) throws JsonProcessingException {
        return CategoryMapper.mapCategoryToCategoryDto(
                this.categoryDao.create(CategoryMapper.mapCategoryDtoToCategory(user)));
    }

    @Override
    public CategoryDto findFullInfo(long id) throws JsonProcessingException {
        return CategoryMapper.mapCategoryToCategoryDto(this.categoryDao.find(id));
    }

    @Override
    public void update(CategoryDto user) throws JsonProcessingException {
        this.categoryDao.update(CategoryMapper.mapCategoryDtoToCategory(user));
    }

    @Override
    public void delete(long id) {
        Category category=this.categoryDao.find(id);
        this.categoryDao.delete(category);
    }

    @Override
    public List<CategoryDto> getAllCategoriesFullInfo() throws JsonProcessingException {
        return CategoryMapper.mapCategoriesToCategoryDtos(this.categoryDao.findAll());
    }

    @Override
    public List<ParentCategoryDto> getParentCategoriesFullInfo() throws JsonProcessingException {
        return CategoryMapper.mapCategoriesToParentCategoryDtos(this.categoryDao.getParentCategories());
    }
}

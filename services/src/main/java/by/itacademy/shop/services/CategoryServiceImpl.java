package by.itacademy.shop.services;

import by.itacademy.shop.api.annotations.LogExceptionCatchable;
import by.itacademy.shop.api.dao.CategoryDao;
import by.itacademy.shop.api.dto.forall.GuestParentCategoryDto;
import by.itacademy.shop.api.dto.admin.AdminCategoryDto;
import by.itacademy.shop.api.dto.admin.AdminParentCategoryDto;
import by.itacademy.shop.api.mappers.CategoryMapper;
import by.itacademy.shop.api.services.CategoryService;
import by.itacademy.shop.entities.Category;
import by.itacademy.shop.utilenum.Lang;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    //-------------User
    @Override
    public GuestParentCategoryDto find(long id, Lang lang) {
        return CategoryMapper.mapCategoryToGuestCategoryDto(this.categoryDao.find(id),lang);
    }

    @Override
    @LogExceptionCatchable
    public List<GuestParentCategoryDto> getParentCategories(Lang lang) {
        return CategoryMapper.mapCategoriesToGuestCategoryDtos(this.categoryDao.getParentCategories(),lang);
    }

    //--------------Admin
    @Override
    public AdminCategoryDto createCategory(AdminCategoryDto user) throws JsonProcessingException {
        return CategoryMapper.mapCategoryToCategoryDto(
                this.categoryDao.create(CategoryMapper.mapCategoryDtoToCategory(user)));
    }

    @Override
    public AdminCategoryDto findFullInfo(long id) throws JsonProcessingException {
        return CategoryMapper.mapCategoryToCategoryDto(this.categoryDao.find(id));
    }

    @Override
    public void update(AdminCategoryDto user) throws JsonProcessingException {
        this.categoryDao.update(CategoryMapper.mapCategoryDtoToCategory(user));
    }

    @Override
    public void delete(long id) {
        Category category=this.categoryDao.find(id);
       this.categoryDao.delete(category);
    }

    @Override
    public List<AdminCategoryDto> getAllCategoriesFullInfo() throws JsonProcessingException {
        return CategoryMapper.mapCategoriesToCategoryDtos(this.categoryDao.findAll());
    }

    @Override
    public List<AdminParentCategoryDto> getParentCategoriesFullInfo() throws JsonProcessingException {
        return CategoryMapper.mapCategoriesToParentCategoryDtos(this.categoryDao.getParentCategories());
    }

    @Override
    public List<AdminCategoryDto> getSubCategoriesFullInfo() throws JsonProcessingException {
        return CategoryMapper.mapCategoriesToCategoryDtos(this.categoryDao.getSubcategories());
    }
}

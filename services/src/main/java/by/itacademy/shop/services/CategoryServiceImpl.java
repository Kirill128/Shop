package by.itacademy.shop.services;

import by.itacademy.shop.api.dao.CategoryDao;
import by.itacademy.shop.api.dto.CategoryDto;
import by.itacademy.shop.api.mappers.CategoryMapper;
import by.itacademy.shop.api.services.CategoryService;
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

    @Override
    public CategoryDto createCategory(CategoryDto user) {
        return null;
    }

    @Override
    public CategoryDto find(long id) {
        return null;
    }

    @Override
    public void update(CategoryDto user) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return CategoryMapper.INSTANCE.mapUserDtos( this.categoryDao.findAll());
    }
}

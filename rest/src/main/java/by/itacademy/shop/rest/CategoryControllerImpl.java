package by.itacademy.shop.rest;

import by.itacademy.shop.api.dto.admin.category.CategoryDto;
import by.itacademy.shop.api.dto.admin.category.ParentCategoryDto;
import by.itacademy.shop.api.mappers.CategoryMapper;
import by.itacademy.shop.api.services.CategoryService;
import by.itacademy.shop.entities.Category;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryControllerImpl {
    private CategoryService categoryService;

    public CategoryControllerImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value="/")
    public ModelAndView showCategories() throws JsonProcessingException {
        ModelAndView modelAndView=new ModelAndView("/admin/categories");
        List<ParentCategoryDto> parentCategoryDtos=this.categoryService.getParentCategoriesFullInfo();
        List<CategoryDto> newCategories=new ArrayList(parentCategoryDtos.size());
        for(ParentCategoryDto parentCategoryDto : parentCategoryDtos){
            newCategories.add( CategoryDto.builder().parentCategoryId(parentCategoryDto.getId()).build() );
        }
        modelAndView.addObject("parentCategories",parentCategoryDtos);
        modelAndView.addObject("newCategoryDtos",newCategories);
        modelAndView.addObject("newParentCategory",new CategoryDto());
        return modelAndView;
    }
    @PostMapping(value = "/create")
    public ModelAndView createCategory(@ModelAttribute CategoryDto category) throws JsonProcessingException {
        this.categoryService.createCategory(category);
        return new ModelAndView("redirect:/categories/");
    }
    @PostMapping(value = "/update")
    public ModelAndView updateCategory(@ModelAttribute CategoryDto category) throws JsonProcessingException {
        this.categoryService.update(category);
        return new ModelAndView("redirect:/categories/");
    }
    @PostMapping("/delete")
    public ModelAndView deleteCategory(@ModelAttribute CategoryDto categoryDto){
        this.categoryService.delete(categoryDto.getId());
        return new ModelAndView("redirect:/categories/");
    }
}

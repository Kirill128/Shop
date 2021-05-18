package by.itacademy.shop.rest.admin;

import by.itacademy.shop.api.annotations.ExceptionCatchable;
import by.itacademy.shop.api.annotations.Loggable;
import by.itacademy.shop.api.dto.admin.CategoryDto;
import by.itacademy.shop.api.dto.admin.ParentCategoryDto;
import by.itacademy.shop.api.services.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ExceptionCatchable
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
    @ExceptionCatchable
    @Loggable
    public ModelAndView createCategory(@ModelAttribute CategoryDto category) throws JsonProcessingException {
        this.categoryService.createCategory(category);
        return new ModelAndView("redirect:/admin/categories");
    }
    @PostMapping(value = "/update")
    @ExceptionCatchable
    @Loggable
    public ModelAndView updateCategory(@ModelAttribute CategoryDto category) throws JsonProcessingException {
        this.categoryService.update(category);
        return new ModelAndView("redirect:/admin/categories");
    }
    @PostMapping("/delete")
    @Loggable
    public ModelAndView deleteCategory(@ModelAttribute CategoryDto categoryDto){
        this.categoryService.delete(categoryDto.getId());
        return new ModelAndView("redirect:/admin/categories");
    }
}
package by.itacademy.shop.rest;

import by.itacademy.shop.api.dto.admin.category.ParentCategoryDto;
import by.itacademy.shop.api.services.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
        modelAndView.addObject("parentCategories",parentCategoryDtos);
        return modelAndView;
    }
}

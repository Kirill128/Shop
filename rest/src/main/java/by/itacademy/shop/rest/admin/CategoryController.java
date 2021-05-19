package by.itacademy.shop.rest.admin;

import by.itacademy.shop.api.annotations.ExceptionCatchable;
import by.itacademy.shop.api.annotations.Log;
import by.itacademy.shop.api.constants.Constants;
import by.itacademy.shop.api.dto.admin.AdminCategoryDto;
import by.itacademy.shop.api.dto.admin.AdminParentCategoryDto;
import by.itacademy.shop.api.services.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(Constants.ROLE_ADMIN_ACCOUNT_CATEGORIES)
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(Constants.ROLE_ADMIN_ACCOUNT_CATEGORIES_ROOT)
    @ExceptionCatchable
    public ModelAndView showCategories() throws JsonProcessingException {
        List<AdminParentCategoryDto> parentCategoryDtos=this.categoryService.getParentCategoriesFullInfo();
        List<AdminCategoryDto> newCategories=new ArrayList(parentCategoryDtos.size());
        for(AdminParentCategoryDto parentCategoryDto : parentCategoryDtos){
            newCategories.add( AdminCategoryDto.builder().parentCategoryId(parentCategoryDto.getId()).build() );
        }
        return new ModelAndView("/admin/categories")
                .addObject("parentCategories",parentCategoryDtos)
                .addObject("newCategoryDtos",newCategories)
                .addObject("newParentCategory",new AdminCategoryDto());
    }

    @PostMapping(Constants.ROLE_ADMIN_ACCOUNT_CATEGORIES_CREATE)
    @ExceptionCatchable
    @Log
    public ModelAndView createCategory(@ModelAttribute AdminCategoryDto category) throws JsonProcessingException {
        this.categoryService.createCategory(category);
        return new ModelAndView("redirect:"+ Constants.ROLE_ADMIN_ACCOUNT_CATEGORIES);
    }

    @PostMapping(Constants.ROLE_ADMIN_ACCOUNT_CATEGORIES_UPDATE)
    @ExceptionCatchable
    @Log
    public ModelAndView updateCategory(@ModelAttribute AdminCategoryDto category) throws JsonProcessingException {
        this.categoryService.update(category);
        return new ModelAndView("redirect:"+ Constants.ROLE_ADMIN_ACCOUNT_CATEGORIES);
    }

    @PostMapping(Constants.ROLE_ADMIN_ACCOUNT_CATEGORIES_DELETE)
    @Log
    public ModelAndView deleteCategory(@ModelAttribute AdminCategoryDto categoryDto){
        this.categoryService.delete(categoryDto.getId());
        return new ModelAndView("redirect:"+ Constants.ROLE_ADMIN_ACCOUNT_CATEGORIES);
    }
}

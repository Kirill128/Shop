package by.itacademy.shop.rest;

import by.itacademy.shop.api.annotations.Log;
import by.itacademy.shop.api.constants.Constants;
import by.itacademy.shop.api.dto.admin.AdminProductDto;
import by.itacademy.shop.api.dto.user.UserDto;
import by.itacademy.shop.api.services.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
@RequestMapping(Constants.ROLE_USER_ACCOUNT_USER)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(Constants.ROLE_USER_ACCOUNT_USER_ACCOUNT)
    public ModelAndView aboutUser(Principal principal){
        return new ModelAndView("/user/account")
                .addObject("user",
                        this.userService.findByEmail(principal.getName(),Constants.GLOBAL_LANG)
                );
    }
    @PostMapping(Constants.ROLE_USER_ACCOUNT_USER_UPDATE)
    @Log
    public ModelAndView update(@ModelAttribute UserDto user){
        return new ModelAndView("redirect:"+Constants.ROLE_USER_ACCOUNT_USER_ACCOUNT);
    }
    @PostMapping(Constants.ROLE_USER_ACCOUNT_USER_DELETE)
    @Log
    public ModelAndView delete(@ModelAttribute UserDto user){
        return new ModelAndView("redirect:"+Constants.ROLE_USER_ACCOUNT_USER_ACCOUNT);
    }

    @PostMapping(Constants.ROLE_USER_ACCOUNT_USER_ADD_ORDER)
    @Log
    public ModelAndView addProductToUserOrdersList(@ModelAttribute AdminProductDto productDto, Principal principal){
        this.userService.addProductToUserOrderList(principal.getName(),productDto.getId());
        return new ModelAndView("redirect:"+Constants.ROLE_USER_ACCOUNT_USER_ACCOUNT);

    }
}

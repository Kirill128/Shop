package by.itacademy.shop.rest;

import by.itacademy.shop.api.annotations.LogExceptionCatchable;
import by.itacademy.shop.api.constants.Constants;
import by.itacademy.shop.api.dto.user.UserDto;
import by.itacademy.shop.api.services.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(Constants.ROLE_GUEST_MAIN)
public class MainController {
    private final UserService userService;

    public MainController( UserService userService) {
        this.userService = userService;
    }

    @GetMapping(Constants.ROLE_GUEST_MAIN_ROOT)
    @LogExceptionCatchable
    public ModelAndView getMainPage(){
        return new ModelAndView("redirect:" +
                Constants.ROLE_GUEST_PRODUCT_SEARCH +
                Constants.ROLE_GUEST_PRODUCT_SEARCH_PAGES +
                "/1");
    }

    @GetMapping(Constants.ROLE_GUEST_MAIN_SIGN_UP)
    public ModelAndView singUp(){
        return new ModelAndView("/singup")
                .addObject("newUser",new UserDto());
    }
    @PostMapping(Constants.ROLE_GUEST_MAIN_SIGN_UP)
    @LogExceptionCatchable
    public ModelAndView createUser(@ModelAttribute UserDto user){
        this.userService.createUser(user, Constants.GLOBAL_LANG);
        return new ModelAndView("redirect:"+Constants.ROLE_GUEST_MAIN);
    }

}

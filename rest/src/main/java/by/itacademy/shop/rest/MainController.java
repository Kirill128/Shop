package by.itacademy.shop.rest;

import by.itacademy.shop.api.constants.Constants;
import by.itacademy.shop.api.dto.user.UserDto;
import by.itacademy.shop.api.services.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping
public class MainController {
    private static final String SING_UP_TEMPLATE="/singup";
    private final UserService userService;

    public MainController( UserService userService) {
        this.userService = userService;
    }



    @GetMapping(Constants.ROLE_GUEST_MAIN_ROOT)
    public ModelAndView getMainPage(){
        return new ModelAndView(Constants.REDIRECT + Constants.ROLE_GUEST_PRODUCT_SEARCH_PAGES + "/1");
    }

    @GetMapping(Constants.ROLE_GUEST_MAIN_SIGN_UP)
    public ModelAndView singUp(){
        return new ModelAndView(SING_UP_TEMPLATE)
                .addObject("userDto",new UserDto());
    }
    @PostMapping(Constants.ROLE_GUEST_MAIN_SIGN_UP)
    public ModelAndView createUser(@ModelAttribute @Valid UserDto user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ModelAndView(SING_UP_TEMPLATE);
        }
        if(this.userService.createUser(user, Constants.GLOBAL_LANG)==null){
            return new ModelAndView(SING_UP_TEMPLATE)
                    .addObject("userExist",true);
        }
        return new ModelAndView(Constants.REDIRECT+Constants.ROLE_GUEST_MAIN_ROOT);
    }

}

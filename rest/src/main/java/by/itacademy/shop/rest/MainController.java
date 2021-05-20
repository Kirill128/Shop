package by.itacademy.shop.rest;

import by.itacademy.shop.api.constants.Constants;
import by.itacademy.shop.api.dto.user.UserDto;
import by.itacademy.shop.api.services.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping
public class MainController {
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
        return new ModelAndView("/singup")
                .addObject("userDto",new UserDto());
    }
    @PostMapping(Constants.ROLE_GUEST_MAIN_SIGN_UP)
    public ModelAndView createUser(@ModelAttribute @Valid UserDto user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ModelAndView("/singup");
        }
        if(this.userService.createUser(user, Constants.GLOBAL_LANG)==null){
            return new ModelAndView("/singup")
                    .addObject("userExist",true);
        }
        return new ModelAndView(Constants.REDIRECT+Constants.ROLE_GUEST_MAIN_ROOT);
    }

}

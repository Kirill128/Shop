package by.itacademy.shop.rest;

import by.itacademy.shop.api.dto.admin.AdminProductDto;
import by.itacademy.shop.api.dto.user.UserDto;
import by.itacademy.shop.api.services.UserService;
import by.senla.daomicroservice.microservices.constants.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
@RequestMapping
@Profile("release")
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
    public ModelAndView update(@ModelAttribute UserDto user){
        return new ModelAndView(Constants.REDIRECT+Constants.ROLE_USER_ACCOUNT_USER_ACCOUNT);
    }
    @PostMapping(Constants.ROLE_USER_ACCOUNT_USER_DELETE)
    public ModelAndView delete(@ModelAttribute UserDto user){
        return new ModelAndView(Constants.REDIRECT+Constants.ROLE_GUEST_MAIN_ROOT);
    }

    @PostMapping(Constants.ROLE_USER_ACCOUNT_USER_ADD_ORDER)
    public ModelAndView addProductToUserOrdersList(@ModelAttribute AdminProductDto productDto, Principal principal) throws JsonProcessingException {
        this.userService.addProductToUserOrderList(principal.getName(),productDto.getId());
        return new ModelAndView(Constants.REDIRECT+Constants.ROLE_USER_ACCOUNT_USER_ACCOUNT);

    }
}

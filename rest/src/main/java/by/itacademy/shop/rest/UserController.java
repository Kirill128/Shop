package by.itacademy.shop.rest;

import by.itacademy.shop.api.annotations.Loggable;
import by.itacademy.shop.api.constants.Constants;
import by.itacademy.shop.api.dto.admin.ProductDto;
import by.itacademy.shop.api.dto.user.UserDto;
import by.itacademy.shop.api.services.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/account")
    public ModelAndView aboutUser(Principal principal){
        ModelAndView modelAndView=new ModelAndView("/user/account");
        UserDto userDto=this.userService.findByEmail(principal.getName(),Constants.GLOBAL_LANG);
        modelAndView.addObject("user",userDto);
        return modelAndView;
    }
    @GetMapping("/orders")
    public ModelAndView userOrders(@ModelAttribute UserDto user){
        ModelAndView modelAndView=new ModelAndView();

        return modelAndView;
    }

    @PostMapping("/create")
    @Loggable
    public ModelAndView createUser(@ModelAttribute UserDto user){
        this.userService.createUser(user,Constants.GLOBAL_LANG);
        return new ModelAndView("redirect:"+Constants.MAIN_PAGE);
    }
    @PostMapping("/update")
    @Loggable
    public ModelAndView update(@ModelAttribute UserDto user){
        return new ModelAndView("redirect:"+Constants.MAIN_PAGE);
    }
    @PostMapping("/delete")
    @Loggable
    public ModelAndView delete(@ModelAttribute UserDto user){
        return new ModelAndView("redirect:"+Constants.MAIN_PAGE);
    }

    @PostMapping("/add-order")
    @Loggable
    public void addProductToUserOrdersList(@ModelAttribute ProductDto productDto,Principal principal){
        this.userService.addProductToUserOrderList(principal.getName(),productDto.getId());
    }
}

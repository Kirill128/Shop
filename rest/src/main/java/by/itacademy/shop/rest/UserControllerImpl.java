package by.itacademy.shop.rest;

import by.itacademy.shop.api.dto.user.UserDto;
import by.itacademy.shop.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/user")
public class UserControllerImpl {

    private UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/info")
    public ModelAndView find(@ModelAttribute UserDto user){
        ModelAndView modelAndView=new ModelAndView();
        UserDto userDto=this.userService.find(user.getId());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createUser(@ModelAttribute UserDto user){
        ModelAndView modelAndView=new ModelAndView();

        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute UserDto user){
        ModelAndView modelAndView=new ModelAndView();

        return modelAndView;
    }
    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute UserDto user){
        ModelAndView modelAndView=new ModelAndView();

        return modelAndView;
    }
}

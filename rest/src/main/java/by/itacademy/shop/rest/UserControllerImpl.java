package by.itacademy.shop.rest;

import by.itacademy.shop.api.annotations.Loggable;
import by.itacademy.shop.api.constants.Constants;
import by.itacademy.shop.api.dto.user.RoleDto;
import by.itacademy.shop.api.dto.user.UserDto;
import by.itacademy.shop.api.services.RoleService;
import by.itacademy.shop.api.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@RequestMapping("/user")
public class UserControllerImpl {//TODO: remove impl

    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;

    public UserControllerImpl(UserService userService, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }


    @GetMapping("/info")
    public ModelAndView aboutUser(@ModelAttribute UserDto user){
        ModelAndView modelAndView=new ModelAndView();
        UserDto userDto=this.userService.find(user.getId());
        return modelAndView;
    }
    @GetMapping("/orders")
    public ModelAndView userOrders(@ModelAttribute UserDto user){
        ModelAndView modelAndView=new ModelAndView();

        return modelAndView;
    }

    @PostMapping("/create")
    @Loggable
    public ModelAndView createUser(@ModelAttribute @Valid UserDto user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ModelAndView res=new ModelAndView("/singup");
            res.addObject("newUser",new UserDto());
            return res;
        }
        //TODO:WTF!!!
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<RoleDto> startRoles=Stream.of(this.roleService.findByName(Constants.NEW_USER_DEFAULT_ROLE)).collect(Collectors.toList());
        user.setRoles(startRoles);
        this.userService.createUser(user);
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
}

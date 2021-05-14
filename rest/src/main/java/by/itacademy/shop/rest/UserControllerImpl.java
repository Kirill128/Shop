package by.itacademy.shop.rest;

import by.itacademy.shop.api.constants.Constants;
import by.itacademy.shop.api.dto.user.RoleDto;
import by.itacademy.shop.api.dto.user.UserDto;
import by.itacademy.shop.api.services.RoleService;
import by.itacademy.shop.api.services.UserService;
//import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@RequestMapping("/user")
public class UserControllerImpl {

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
    public ModelAndView createUser(@ModelAttribute  UserDto user){
//        if(bindingResult.hasErrors()){
//            ModelAndView res=new ModelAndView("/singup");
//            res.addObject("bindingResultCreate",bindingResult);
//            return res;
//        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<RoleDto> startRoles=Stream.of(this.roleService.findByName(Constants.NEW_USER_DEFAULT_ROLE)).collect(Collectors.toList());
        user.setRoles(startRoles);
        this.userService.createUser(user);
        return new ModelAndView("redirect:"+Constants.MAIN_PAGE);
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute UserDto user){
        return new ModelAndView("redirect:"+Constants.MAIN_PAGE);
    }
    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute UserDto user){
        return new ModelAndView("redirect:"+Constants.MAIN_PAGE);
    }
}

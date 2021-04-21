package by.itacademy.shop.rest;

import by.itacademy.shop.api.dto.UserDto;
import by.itacademy.shop.api.services.UserService;
import liquibase.pro.packaged.M;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/user")
public class UserControllerImpl {

    private UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String defaultPage(){
        return "<h1>Hello</h1>";
    }
    @PostMapping("/create")
    public UserDto createUser(UserDto user){
        return null;
    }

    @GetMapping("/{id}")
    public String find(@PathVariable int id){
        return "<h1>"+this.userService.find(id).toString()+"</h1>";
    }

    @GetMapping("/all")
    public ModelAndView showAllUsers(){
        ModelAndView modelAndView=new ModelAndView("/admin/users");
        modelAndView.addObject("userList",this.userService.getAllUsers());
        return modelAndView;
    }
}

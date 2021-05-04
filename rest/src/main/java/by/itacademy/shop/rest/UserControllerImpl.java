package by.itacademy.shop.rest;

import by.itacademy.shop.api.dto.admin.UserDto;
import by.itacademy.shop.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/users")
public class UserControllerImpl {

    private UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }
    //--------------------

    //--------------------
    @GetMapping("/{id}")
    public ModelAndView find(@PathVariable int id){
        UserDto userDto=this.userService.find(id);
        return null;
    }

    @GetMapping("/")
    public ModelAndView findAllUsers(){
        ModelAndView modelAndView=new ModelAndView("/admin/users");
        modelAndView.addObject("userList",this.userService.getAllUsers());
        return modelAndView;
    }

    @PostMapping("/")
    public ModelAndView createUser(UserDto user){
        return null;
    }

    @PutMapping("/")
    public ModelAndView update(@RequestBody UserDto user){
        return null;
    }
    @DeleteMapping("/{id}}")
    public ModelAndView delete(@PathVariable int id){
        return null;
    }
}

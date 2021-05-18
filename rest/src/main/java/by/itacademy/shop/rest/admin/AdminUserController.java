package by.itacademy.shop.rest.admin;

import by.itacademy.shop.api.annotations.Loggable;
import by.itacademy.shop.api.dto.admin.AdminUserDto;
import by.itacademy.shop.api.dto.user.UserDto;
import by.itacademy.shop.api.services.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/admin/users")
public class AdminUserController {

    private UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public ModelAndView find(@PathVariable int id){
        ModelAndView modelAndView=new ModelAndView();
//        AdminUserDto userDto=this.userService.findFullInfo(id);
        return modelAndView;
    }

    @GetMapping
    public ModelAndView findAllUsers(){
        ModelAndView modelAndView=new ModelAndView("/admin/users");
        modelAndView.addObject("userList",this.userService.getAllUsers());
        return modelAndView;
    }


    @PostMapping("/update")
    @Loggable
    public ModelAndView update(@RequestBody UserDto user){
        ModelAndView modelAndView=new ModelAndView();

        return modelAndView;
    }
    @PostMapping("/delete")
    @Loggable
    public ModelAndView delete(@PathVariable int id){
        ModelAndView modelAndView=new ModelAndView();

        return modelAndView;
    }
}

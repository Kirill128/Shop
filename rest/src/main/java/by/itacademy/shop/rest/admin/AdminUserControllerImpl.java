package by.itacademy.shop.rest.admin;

import by.itacademy.shop.api.dto.user.UserDto;
import by.itacademy.shop.api.services.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/admin/users")
public class AdminUserControllerImpl {

    private UserService userService;

    public AdminUserControllerImpl(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public ModelAndView find(@PathVariable int id){
        ModelAndView modelAndView=new ModelAndView();
        UserDto userDto=this.userService.find(id);
        return modelAndView;
    }

    @GetMapping
    public ModelAndView findAllUsers(){
        ModelAndView modelAndView=new ModelAndView("/admin/users");
        modelAndView.addObject("userList",this.userService.getAllUsers());
        return modelAndView;
    }


    @PostMapping("/update")
    public ModelAndView update(@RequestBody UserDto user){
        ModelAndView modelAndView=new ModelAndView();

        return modelAndView;
    }
    @PostMapping("/delete")
    public ModelAndView delete(@PathVariable int id){
        ModelAndView modelAndView=new ModelAndView();

        return modelAndView;
    }
}

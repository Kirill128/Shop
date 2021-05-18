package by.itacademy.shop.rest;

import by.itacademy.shop.api.annotations.Loggable;
import by.itacademy.shop.api.constants.Constants;
import by.itacademy.shop.api.dto.user.UserDto;
import by.itacademy.shop.api.mappers.RoleMapper;
import by.itacademy.shop.api.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping
public class MainController {
    private AuthenticationManager authenticationManager;
    private UserService userService;


    public MainController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @GetMapping
    @Loggable
    public ModelAndView getMainPage(Authentication authentication){
        return new ModelAndView("redirect:/products/pages/1");
    }

    @GetMapping("/sing-up")
    public ModelAndView singUp(){
        ModelAndView modelAndView=new ModelAndView("/singup");
        modelAndView.addObject("newUser",new UserDto());
        return modelAndView;
    }
    @PostMapping("/sing-up")
    @Loggable
    public ModelAndView createUser(@ModelAttribute UserDto user){
        UserDto newUser=this.userService.createUser(user, Constants.GLOBAL_LANG);

        Authentication authToken = new UsernamePasswordAuthenticationToken(
                newUser.getEmail(),
                newUser.getPassword(),
                RoleMapper.mapRoleDtosToAuthorities(newUser.getRoles()));
        Authentication authenticationRes = this.authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authenticationRes);

        return new ModelAndView("redirect:"+Constants.MAIN_PAGE);
    }

}

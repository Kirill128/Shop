package by.itacademy.shop.rest.admin;

import by.itacademy.shop.api.annotations.ExceptionCatchable;
import by.itacademy.shop.api.annotations.Log;
import by.itacademy.shop.api.constants.Constants;
import by.itacademy.shop.api.dto.admin.AdminUserDto;
import by.itacademy.shop.api.services.RoleService;
import by.itacademy.shop.api.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(Constants.ROLE_ADMIN_ACCOUNT_USERS)
public class AdminUserController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminUserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/{id}")
    @ExceptionCatchable
    public ModelAndView find(@PathVariable int id) throws JsonProcessingException {
        return new ModelAndView("/admin/user-account")
                .addObject("user",this.userService.findFullInfo(id));
    }

    @GetMapping
    @ExceptionCatchable
    public ModelAndView findAllUsers() throws JsonProcessingException {
        return new ModelAndView("/admin/users")
                .addObject("userList",this.userService.getAllUsers())
                .addObject("allRoles",this.roleService.getAllRoles());
    }

    @ExceptionCatchable
    @PostMapping("/set-role")
    public ModelAndView setRole(@ModelAttribute AdminUserDto adminUserDto) throws JsonProcessingException {
        this.userService.setRole(adminUserDto);
        return new ModelAndView("redirect:"+ Constants.ROLE_ADMIN_ACCOUNT_USERS);
    }
    @ExceptionCatchable
    @PostMapping("/delete-role")
    public ModelAndView deleteRole(@ModelAttribute AdminUserDto adminUserDto) throws JsonProcessingException {
        this.userService.deleteRole(adminUserDto);
        return new ModelAndView("redirect:"+ Constants.ROLE_ADMIN_ACCOUNT_USERS);
    }

    @PostMapping("/delete")
    @Log
    public ModelAndView delete(@ModelAttribute AdminUserDto adminUserDto){
        this.userService.delete(adminUserDto.getId());
        return new ModelAndView("redirect:"+ Constants.ROLE_ADMIN_ACCOUNT_USERS);
    }
}

package by.itacademy.shop.rest.admin;

import by.itacademy.shop.api.annotations.LogExceptionCatchable;
import by.itacademy.shop.api.constants.Constants;
import by.itacademy.shop.api.dto.admin.AdminUserDto;
import by.itacademy.shop.api.services.RoleService;
import by.itacademy.shop.api.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping
@Profile("release")
public class AdminUserController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminUserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(Constants.ROLE_ADMIN_ACCOUNT_USERS_ID)
    public ModelAndView find(@PathVariable int id) throws JsonProcessingException {
        return new ModelAndView("/admin/user-account")
                .addObject("user",this.userService.findFullInfo(id));
    }

    @GetMapping(Constants.ROLE_ADMIN_ACCOUNT_USERS_ROOT)
    public ModelAndView findAllUsers() throws JsonProcessingException {
        return new ModelAndView("/admin/users")
                .addObject("userList",this.userService.getAllUsers())
                .addObject("allRoles",this.roleService.getAllRoles());
    }

    @LogExceptionCatchable
    @PostMapping(Constants.ROLE_ADMIN_ACCOUNT_USERS_SET_ROLE)
    public ModelAndView setRole(@ModelAttribute AdminUserDto adminUserDto)  {
        this.userService.setRole(adminUserDto);
        return new ModelAndView(Constants.REDIRECT+ Constants.ROLE_ADMIN_ACCOUNT_USERS_ROOT);
    }
    @LogExceptionCatchable
    @PostMapping(Constants.ROLE_ADMIN_ACCOUNT_USERS_DELETE_ROLE)
    public ModelAndView deleteRoleFromUser(@ModelAttribute AdminUserDto adminUserDto)  {
        this.userService.deleteRole(adminUserDto);
        return new ModelAndView(Constants.REDIRECT+ Constants.ROLE_ADMIN_ACCOUNT_USERS_ROOT);
    }

    @PostMapping(Constants.ROLE_ADMIN_ACCOUNT_USERS_DELETE)
    @LogExceptionCatchable
    public ModelAndView delete(@ModelAttribute AdminUserDto adminUserDto){
        this.userService.delete(adminUserDto.getId());
        return new ModelAndView(Constants.REDIRECT+ Constants.ROLE_ADMIN_ACCOUNT_USERS_ROOT);
    }
}

package by.itacademy.shop.services;

import by.itacademy.shop.api.dao.UserDao;
import by.itacademy.shop.api.dto.user.UserDto;
import by.itacademy.shop.api.mappers.RoleMapper;
import by.itacademy.shop.api.mappers.UserMapper;
import by.itacademy.shop.api.services.UserService;
import by.itacademy.shop.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    //---------------------------------CRUD----------------------------------------------------
    @Override
    public UserDto createUser(UserDto user) {
        return UserMapper.mapUserToUserDto(this.userDao.create(UserMapper.mapUserDtoToUser(user)));
    }

    @Override
    public UserDto find(long id) {
        return UserMapper.mapUserToUserDto(this.userDao.find(id));
    }

    @Override
    public void update(UserDto user) {
        this.userDao.update(UserMapper.mapUserDtoToUser(user));
    }

    @Override
    public void delete(long id) {
        User user = this.userDao.find(id);
        this.userDao.delete(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return UserMapper.mapUsersToUserDtos(this.userDao.findAll());
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User foundUser=this.userDao.findByEmail(email);
        if(foundUser==null)throw new UsernameNotFoundException(String.format("User whit Email '%s' not found!!",email));
        return  org.springframework.security.core.userdetails.User.builder().
                username(foundUser.getEmail()).
                password(foundUser.getPassword()).
                authorities(RoleMapper.mapRolesToAuthorities(foundUser.getRoles())).
                build();
    }

    //-------------------------------------------------------------------------------------
}

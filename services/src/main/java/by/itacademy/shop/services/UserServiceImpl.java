package by.itacademy.shop.services;

import by.itacademy.shop.api.dao.UserDao;
import by.itacademy.shop.api.dto.user.UserDto;
import by.itacademy.shop.api.mappers.UserMapper;
import by.itacademy.shop.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao){
        this.userDao=userDao;
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
        this.userDao.delete(id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return UserMapper.mapUsersToUserDtos(this.userDao.findAll());
    }
    //-------------------------------------------------------------------------------------
}

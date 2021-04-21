package by.itacademy.shop.services;

import by.itacademy.shop.api.dao.UserDao;
import by.itacademy.shop.api.dto.UserDto;
import by.itacademy.shop.api.mappers.UserMapper;
import by.itacademy.shop.api.services.UserService;
import by.itacademy.shop.entities.User;
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

    @Override
    public UserDto createUser(UserDto user) {
        return UserMapper.INSTANCE.mapUserDto(this.userDao.create(UserMapper.INSTANCE.mapUser(user)));
    }

    @Override
    public UserDto find(int id) {
        return UserMapper.INSTANCE.mapUserDto(this.userDao.find(id));
    }

    @Override
    public void update(UserDto user) {
        this.userDao.update(UserMapper.INSTANCE.mapUser(user));
    }

    @Override
    public void delete(int id) {
        User user=this.userDao.find(id);
        this.userDao.delete(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return UserMapper.INSTANCE.mapUserDtos(this.userDao.findAll());
    }
}

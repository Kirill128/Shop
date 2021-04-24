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
    public UserDto find(long id) {
        User user=this.userDao.find(id);
        return (user!=null)? UserMapper.INSTANCE.mapUserDto(user) : null;
    }

    @Override
    public void update(UserDto user) {
//        User userUpd=this.userDao.find(user.getId());
//        if(userUpd!=null){
//            if(user.getName()!=null)userUpd.setName(user.getName());
//            if(user.getEmail()!=null)userUpd.setEmail(user.getEmail());
//            if(user.getPassword()!=null)userUpd.setPassword(user.getPassword());
//            if(user.getPhone()!=null)userUpd.setPhone(user.getPhone());
//            if(user.getOrders()!=null)userUpd.setOrders(user.getOrders());
//            this.userDao.update(userUpd);
//        }
    }

    @Override
    public void delete(long id) {
        User user=this.userDao.find(id);
        if(user!=null)this.userDao.delete(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return UserMapper.INSTANCE.mapUserDtos(this.userDao.findAll());
    }
}

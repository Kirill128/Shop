package by.itacademy.shop.services;

import by.itacademy.shop.api.constants.Constants;
import by.itacademy.shop.api.dao.*;
import by.itacademy.shop.api.dto.admin.AdminUserDto;
import by.itacademy.shop.api.dto.user.UserDto;
import by.itacademy.shop.api.mappers.RoleMapper;
import by.itacademy.shop.api.mappers.UserMapper;
import by.itacademy.shop.api.services.UserService;
import by.itacademy.shop.entities.*;
import by.itacademy.shop.forentity.Status;
import by.itacademy.shop.utilenum.Lang;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private UserDao userDao;
    private ProductDao productDao;
    private RoleDao roleDao;
    private OrderDao orderDao;
    private ProductOrderDao productOrderDao;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao,
                           ProductDao productDao,
                           RoleDao roleDao,
                           OrderDao orderDao,
                           ProductOrderDao productOrderDao,
                           PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.productDao = productDao;
        this.roleDao = roleDao;
        this.orderDao = orderDao;
        this.productOrderDao = productOrderDao;
        this.passwordEncoder = passwordEncoder;
    }


    //---------------------------------CRUD----------------------------------------------------

    @Override
    public UserDto createUser(UserDto user, Lang lang) {
        User newUser= UserMapper.mapUserDtoToUser(user);
        newUser.setPassword(this.passwordEncoder.encode(user.getPassword()));
        Role role=this.roleDao.findByName(Constants.NEW_USER_DEFAULT_ROLE);
        List<Role> startRoles = Stream.of(role).collect(Collectors.toList());
        newUser.setRoles(startRoles);
        this.userDao.create(newUser);
        return UserMapper.mapUserToUserDto(newUser,lang);
    }

    @Override
    public UserDto find(long id,Lang lang) {
        return UserMapper.mapUserToUserDto(this.userDao.find(id),lang);
    }

    @Override
    public AdminUserDto findFullInfo(long id) {
        return UserMapper.mapUserToAdminUserDto(this.userDao.find(id));
    }

    @Override
    public UserDto findByEmail(String email,Lang lang) {
        return UserMapper.mapUserToUserDto(this.userDao.findByEmail(email),lang);
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
    public List<AdminUserDto> getAllUsers() {
        return UserMapper.mapUsersToAdminUserDtos(this.userDao.findAll());
    }

    @Override
    public void addProductToUserOrderList(String email, long productId) {
        User user=this.userDao.findByEmail(email);
        Product product=this.productDao.find(productId);
        for(Order order : user.getOrders()){
            if(order.getStatus().equals(Status.NOT_PAID)){
                for(ProductOrder productOrder: order.getProductOrder()){
                    if(productOrder.getProduct().getId()==productId){
                        productOrder.setQuantity(productOrder.getQuantity()+1);
                        productOrder.setPrice(productOrder.getPrice()+product.getPrice());
                        order.setPrice(order.getPrice()+product.getPrice());
                        this.productOrderDao.update(productOrder);
                        this.orderDao.update(order);
                        return;
                    }
                }
                ProductOrder newProductOrderIfOrderExists=ProductOrder.builder()
                        .quantity(1)
                        .order(order)
                        .product(product)
                        .price(product.getPrice())
                        .build();
                order.getProductOrder().add(newProductOrderIfOrderExists);
                order.setPrice(order.getPrice()+product.getPrice());
                this.productOrderDao.create(newProductOrderIfOrderExists);
                this.orderDao.update(order);
                return;
            }
        }
        Order newOrderIfDoesntExist=Order.builder()
                .status(Status.NOT_PAID)
                .user(user)
                .price(product.getPrice())
                .build();
        ProductOrder newProductOrderIfOrderDoesntExist=ProductOrder.builder()
                .quantity(1)
                .order(newOrderIfDoesntExist)
                .product(product)
                .price(product.getPrice())
                .build();
        this.orderDao.create(newOrderIfDoesntExist);
        this.productOrderDao.create(newProductOrderIfOrderDoesntExist);
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

}

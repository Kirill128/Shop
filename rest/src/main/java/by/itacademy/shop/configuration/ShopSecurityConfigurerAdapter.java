//package by.itacademy.shop.configuration;
//
//import by.itacademy.shop.api.services.UserService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Profile;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@EnableWebSecurity
//@Profile("release")
//public class ShopSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
//
//    private UserService userService;
//    private PasswordEncoder passwordEncoder;
//
//    public ShopSecurityConfigurerAdapter(UserService userService, PasswordEncoder passwordEncoder) {
//        this.userService = userService;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests()
//                .antMatchers("/","/errors/*").permitAll()
//                .antMatchers("/sing-up","/login").anonymous()
//                .antMatchers("/admin/*").hasRole("ADMIN")
//                .antMatchers("/user/*").hasRole("USER")
//                .and()
//                .formLogin()
//                .defaultSuccessUrl("/")
//                .and()
//                .logout().logoutSuccessUrl("/");
//    }
//
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(this.passwordEncoder);
//        provider.setUserDetailsService(this.userService);
//        return provider;
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//}

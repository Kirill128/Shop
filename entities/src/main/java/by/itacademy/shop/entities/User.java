package by.itacademy.shop.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shop_user")
public class User extends GenericEntity<Long> {

    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name="user_id") },
            inverseJoinColumns = {@JoinColumn(name="role_id")})
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user")
    private List<Order> orders;


}


package by.itacademy.shop.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role extends GenericEntity<Long>{

    @Column(name = "name")
    private String name;

//    @ManyToMany(mappedBy = "roles")
//    private List<User> users;
}

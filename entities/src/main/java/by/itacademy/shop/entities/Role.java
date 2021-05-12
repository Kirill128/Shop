package by.itacademy.shop.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role extends GenericEntity<Long>{

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "roles",cascade = CascadeType.REMOVE)
    private List<User> users;

}

package by.itacademy.shop.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public enum Role {
    ROLE_ADMIN,
    ROLE_USER
}

package by.itacademy.shop.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@javax.persistence.Entity
@Table(name = "product")
public class Product extends Entity<Long>{

    @Column(name="name")
    private String name;

    @Column(name="price")
    private Double price;

    @Column(name="quantity_in_storage")
    private Integer quantityInStorage;

}

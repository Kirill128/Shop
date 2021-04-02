package by.itacademy.shop.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@SuperBuilder
@ToString
@javax.persistence.Entity
@Table(name = "product")
public class Product extends GenericEntity<Long>{

    @Column(name="short_description")
    private String shortDescription;

    @Column(name="price")
    private Double price;

    @Column(name="quantity_in_storage")
    private Integer quantityInStorage;

    private Category category;

    private Photo photo;

    private Provider provider;

    private Map<String,String> attributes;
}

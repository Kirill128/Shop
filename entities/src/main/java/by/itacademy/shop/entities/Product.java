package by.itacademy.shop.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "product")
public class Product extends GenericEntity<Long>{

    @Column(name="short_description")
    private String shortDescription;

    @Column(name="price")
    private Double price;

    @Column(name="quantity_in_storage")
    private Integer quantityInStorage;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id")
    private Category category;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id")
    private Photo photo;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id")
    private Provider provider;

//    private String attributes;
//    private Map<String,String> attributes;
}

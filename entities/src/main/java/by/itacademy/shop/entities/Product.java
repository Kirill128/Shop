package by.itacademy.shop.entities;

import by.itacademy.shop.converter.JsonMapConverter;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@Entity
@Table(name = "product")
public class Product extends GenericEntity<Long>{

    @Column(name="short_description")
    @Convert(converter = JsonMapConverter.class)
    private Map<String,String> shortDescription;

    @Column(name="price")
    private Double price;

    @Column(name="quantity_in_storage")
    private Integer quantityInStorage;

    @ManyToOne(fetch=FetchType.LAZY)//,cascade = CascadeType.PERSIST)
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToOne(fetch=FetchType.LAZY)//,cascade = CascadeType.PERSIST)
    @JoinColumn(name="photo_id")
    private Photo photo;

    @ManyToOne(fetch=FetchType.LAZY)//,cascade = CascadeType.PERSIST)
    @JoinColumn(name="provider_id")
    private Provider provider;

    @Convert(converter = JsonMapConverter.class)
    private Map<String,String> attributes;

}

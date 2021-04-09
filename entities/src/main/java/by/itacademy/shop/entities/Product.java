package by.itacademy.shop.entities;

import by.itacademy.shop.converter.JsonMapConverter;
import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
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

    @Convert(converter = JsonMapConverter.class)
    @Column(name = "attributes")
    private Map<String,String> attributes;

    @Convert(converter = JsonMapConverter.class)
    @Column(name="short_description")
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


}

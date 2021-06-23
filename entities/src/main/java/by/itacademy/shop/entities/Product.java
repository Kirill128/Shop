package by.itacademy.shop.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "main-page-entity-graph",
                attributeNodes = {
                        @NamedAttributeNode(value = "category"),
                        @NamedAttributeNode(value = "photo"),
                        @NamedAttributeNode(value = "provider")
                }
        )
})
@Entity
@Table(name = "product")
public class Product extends GenericEntity<Long>{

    @Type(type = "jsonb")
    @Column(name = "attributes",columnDefinition = "jsonb")
    private Map<String,String> attributes;

    @Type(type = "jsonb")
    @Column(name="short_description",columnDefinition = "jsonb")
    private Map<String,String> shortDescription;

    @Column(name="price")
    private Double price;

    @Column(name="barcode")
    private String barcode;

    @Column(name="quantity_in_storage")
    private Integer quantityInStorage;

    @ManyToOne(fetch=FetchType.LAZY)//,cascade = CascadeType.PERSIST)
    @JoinColumn(name="category_id" )
    private Category category;

    @ManyToOne(fetch=FetchType.LAZY)//,cascade = CascadeType.ALL)
    @JoinColumn(name="photo_id")
    private Photo photo;

    @ManyToOne(fetch=FetchType.LAZY)//,cascade = CascadeType.PERSIST)
    @JoinColumn(name="provider_id")
    private Provider provider;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "product",cascade = CascadeType.ALL)
    private List<ProductOrder> productOrders;


}

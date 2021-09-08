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
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category extends GenericEntity<Long>{

    @Type(type = "jsonb")
    @Column(name = "title",columnDefinition = "jsonb")
    private Map<String,String> title;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parentCategory;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "parentCategory",cascade = CascadeType.REMOVE)
    private List<Category> subCategories;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "category",cascade = CascadeType.ALL)
    private List<Product> products;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "category_attribute",
            joinColumns = {@JoinColumn(name="category_id") },
            inverseJoinColumns = {@JoinColumn(name="attribute_id")})
    private List<Attribute> attributes;

    @Override
    public String toString() {
        return "Category{" +
                "title=" + title +
//                ", parentCategory=" + parentCategory +
                ", subCategories=" + subCategories +
                ", id=" + id +
                '}';
    }
}

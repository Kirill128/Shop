package by.itacademy.shop.entities;

import by.itacademy.shop.converter.JsonMapConverter;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="category")
public class Category extends GenericEntity<Long>{

    @Convert(converter = JsonMapConverter.class)
    private Map<String,String> title;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parentCategory;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "category")
    private List<Product> products;

    @Override
    public String toString() {
        return "Category{" +
                "title=" + title +
                ", parentCategory=" + parentCategory +
//                ", products=" + products +
                ", id=" + id +
                '}';
    }
}


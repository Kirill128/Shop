package com.example.daotest.entities;

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
@Table(name="category")
public class Category extends GenericEntity<Long> {

    @Type(type = "json")
    private Map<String,String> title;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parentCategory;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "parentCategory",cascade = CascadeType.REMOVE)
    private List<Category> subCategories;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "category",cascade = CascadeType.ALL)
    private List<Product> products;

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


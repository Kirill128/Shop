package com.example.daotest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "provider")
public class Provider extends GenericEntity<Long> {

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "provider")
    private List<Product> products;


}

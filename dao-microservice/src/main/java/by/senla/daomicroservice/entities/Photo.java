package by.senla.daomicroservice.entities;

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
@Table(name = "photo")
public class Photo extends GenericEntity<Long>{

    private String url;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "photo")
    private List<Product> products;



}

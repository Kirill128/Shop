package by.itacademy.shop.entities;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class),
        @TypeDef(name = "json", typeClass = JsonStringType.class)
})
@MappedSuperclass
public abstract class GenericEntity<T> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    protected T id;

}

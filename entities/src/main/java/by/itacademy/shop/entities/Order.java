package by.itacademy.shop.entities;

import by.itacademy.shop.converter.StatusConverter;
import by.itacademy.shop.forentity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shop_order")
public class Order  extends GenericEntity<Long>{

    @Convert(converter = StatusConverter.class)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="submit_time")
    private LocalDateTime submitTime;

    @Column(name="price")
    private Double price;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "order",cascade = CascadeType.REMOVE)
    private List<ProductOrder> productOrder;

}

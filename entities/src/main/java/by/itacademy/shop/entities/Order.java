package by.itacademy.shop.entities;

import by.itacademy.shop.converter.StatusConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.GregorianCalendar;
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

    private Double price;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "order")
    private List<ProductOrder> productOrder;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                ", submitTime=" + submitTime +
                ", price=" + price +
                '}';
    }
}

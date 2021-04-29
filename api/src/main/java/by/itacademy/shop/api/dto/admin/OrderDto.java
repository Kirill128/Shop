package by.itacademy.shop.api.dto.admin;

import by.itacademy.shop.entities.Status;
import by.itacademy.shop.entities.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Status status;
    private Long id;
    private User user;

    private LocalDateTime submitTime;

    private Double price;

}

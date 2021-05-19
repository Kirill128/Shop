package by.itacademy.shop.api.dto.admin;

import by.itacademy.shop.forentity.Status;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminOrderDto {
    private Long id;
    private Status status;
    private LocalDateTime submitTime;
    private Double price;
    private List<AdminProductOrderDto> productOrder;
}

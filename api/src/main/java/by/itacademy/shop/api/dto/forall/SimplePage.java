package by.itacademy.shop.api.dto.forall;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimplePage<T> {
    private List<T> results;
    private long countInDb;
}
